package com.amrk000.yajhaz.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.amrk000.yajhaz.R
import com.amrk000.yajhaz.databinding.FragmentSignInBinding
import com.amrk000.yajhaz.databinding.FragmentSignUpBinding
import com.amrk000.yajhaz.model.request.SignInRequestModel
import com.amrk000.yajhaz.model.request.SignUpRequestModel
import com.amrk000.yajhaz.model.response.ResponseModel
import com.amrk000.yajhaz.util.TextFieldValidator
import com.amrk000.yajhaz.viewModel.fragments.SignInViewModel
import com.amrk000.yajhaz.viewModel.fragments.SignUpViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignIn : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model init
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        //nav controller init
        navController = Navigation.findNavController(view)

        //loading dialog
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        binding.signInForgotPasswordButton.setOnClickListener {
            Toast.makeText(context, "Forgot password page", Toast.LENGTH_LONG).show()
        }

        binding.signInSignUpButton.setOnClickListener {
            navController.navigate(R.id.action_signIn_to_signUp)
        }

        binding.signInSignButton.setOnClickListener {
            if(allValid()) {
                viewModel.signInUser(
                    SignInRequestModel(
                        binding.signInEmailField.editText?.text.toString(),
                        binding.signInPasswordField.editText?.text.toString(),
                    )
                )

                progressDialog.show()
            }
        }

        viewModel.getSignInUserObserver().observe(viewLifecycleOwner) {
                userProfile ->

            progressDialog.dismiss()

            when(userProfile.code){
                ResponseModel.SUCCESSFUL_OPERATION -> {
                    navController.navigate(
                        R.id.action_signIn_to_home,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.signIn, true).build()
                    )
                }
                ResponseModel.FAILED_INVALID_DATA -> {
                    Toast.makeText(context, "Wrong email or password !", Toast.LENGTH_LONG).show()

                }
                else -> {
                    Toast.makeText(context, "Error getting data form server !", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    fun allValid(): Boolean{
        var allValid = true

        //Email Field
        if(! TextFieldValidator.isValidEmail(binding.signInEmailField.editText?.text.toString())){
            binding.signInEmailField.error = "please enter a valid email"
            allValid = false
        }
        else binding.signInEmailField.error = null

        //password Field
        if(! TextFieldValidator.isValidPassword(binding.signInPasswordField.editText?.text.toString())){
            binding.signInPasswordField.error = "password should be: 8 chars with upper, lower and symbols"
            allValid = false
        }
        else binding.signInPasswordField.error = null

        return allValid
    }

}