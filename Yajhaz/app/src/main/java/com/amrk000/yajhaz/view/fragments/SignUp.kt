package com.amrk000.yajhaz.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.amrk000.yajhaz.R
import com.amrk000.yajhaz.databinding.FragmentSignUpBinding
import com.amrk000.yajhaz.model.request.SignUpRequestModel
import com.amrk000.yajhaz.model.response.ResponseModel
import com.amrk000.yajhaz.util.TextFieldValidator
import com.amrk000.yajhaz.viewModel.fragments.SignUpViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUp : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model init
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        //nav controller init
        navController = Navigation.findNavController(view)

        //loading dialog
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        binding.signUpSignInButton.setOnClickListener {
            navController.navigate(R.id.action_signUp_to_signIn)
        }

        binding.signUpSignButton.setOnClickListener {
            if(allValid()) {
                viewModel.signUpUser(
                    SignUpRequestModel(
                        binding.signUpEmailField.editText?.text.toString(),
                        binding.signUpPasswordField.editText?.text.toString(),
                        binding.signUpPhoneNumberField.editText?.text.toString(),
                        binding.signUpNameField.editText?.text.toString()
                    )
                )

                progressDialog.show()
            }
        }

        viewModel.getSignUpUserObserver().observe(viewLifecycleOwner) {
                userProfile ->

            progressDialog.dismiss()

            when(userProfile.code){
                ResponseModel.SUCCESSFUL_OPERATION -> {
                    val alertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
                    alertDialogBuilder.setCancelable(true)
                    alertDialogBuilder.setTitle("Signed Up Successfully")
                    alertDialogBuilder.setMessage("you can sign in to your account")
                    alertDialogBuilder.setPositiveButton("close") { dialog, which ->
                        dialog.dismiss()
                    }
                    alertDialogBuilder.create()
                    alertDialogBuilder.show()

                    navController.navigate(R.id.action_signUp_to_signIn)

                }
                ResponseModel.FAILED_INVALID_DATA -> {
                    Toast.makeText(context, "You already have an account", Toast.LENGTH_LONG).show()

                }
                else -> {
                    Toast.makeText(context, "Error getting data form server !", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    fun allValid(): Boolean{
        var allValid = true

        //Name Field
        if(! TextFieldValidator.isValidName(binding.signUpNameField.editText?.text.toString())){
            binding.signUpNameField.error = "please enter your full name"
            allValid = false
        }
        else binding.signUpNameField.error = null

        //Email Field
        if(! TextFieldValidator.isValidEmail(binding.signUpEmailField.editText?.text.toString())){
            binding.signUpEmailField.error = "please enter a valid email"
            allValid = false
        }
        else binding.signUpEmailField.error = null

        //phone Field
        if(! TextFieldValidator.isValidPhone(binding.signUpPhoneNumberField.editText?.text.toString())){
            binding.signUpPhoneNumberField.error = "please enter a valid number"
            allValid = false
        }
        else binding.signUpPhoneNumberField.error = null

        //password Field
        if(! TextFieldValidator.isValidPassword(binding.signUpPasswordField.editText?.text.toString())){
            binding.signUpPasswordField.error = "password should be: 8 chars with upper, lower and symbols"
            allValid = false
        }
        else binding.signUpPasswordField.error = null

        //confirm password Field
        if(! binding.signUpPasswordField.editText?.text.toString().equals(binding.signUpConfirmPasswordField.editText?.text.toString())){
            binding.signUpConfirmPasswordField.error = "password not matching !"
            allValid = false
        }
        else binding.signUpConfirmPasswordField.error = null

        return allValid
    }

}