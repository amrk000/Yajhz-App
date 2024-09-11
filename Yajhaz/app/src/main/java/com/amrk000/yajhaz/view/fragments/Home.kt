package com.amrk000.yajhaz.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrk000.yajhaz.R
import com.amrk000.yajhaz.adapter.CategoriesAdapter
import com.amrk000.yajhaz.adapter.PopularSellersAdapter
import com.amrk000.yajhaz.adapter.TrendingSellersAdapter
import com.amrk000.yajhaz.databinding.FragmentHomeBinding
import com.amrk000.yajhaz.model.response.ResponseModel
import com.amrk000.yajhaz.util.SharedPrefManager
import com.amrk000.yajhaz.viewModel.fragments.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model init
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //nav controller init
        navController = Navigation.findNavController(view)

        //load cached user profile
        val userData = viewModel.getCachedUserData()

        if(userData != null) {
            binding.homeGreetings.text = "Hello, ${userData.name}"

            val address = userData.addresses.get(0).address.toString() + userData.addresses.get(0).street + " Street"
            binding.homeAddress.text = address
        }

        binding.homeBackButton.setOnClickListener {
            val alertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.setTitle("Sign out ?")
            alertDialogBuilder.setPositiveButton("yes") { dialog, which ->
                viewModel.signOut()

                navController.navigate(
                    R.id.action_home_to_signIn,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.home, true).build()
                )

            }
            alertDialogBuilder.setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }
            alertDialogBuilder.create()
            alertDialogBuilder.show()
        }

        binding.homeCartButton.setOnClickListener {
            Toast.makeText(context, "My Cart", Toast.LENGTH_SHORT).show()
        }

        binding.homeMenuButton.setOnClickListener {
            Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
        }

        binding.homeAddress.setOnClickListener {
            Toast.makeText(context, "Edit Address", Toast.LENGTH_SHORT).show()
        }

        binding.homeSearchButton.setOnClickListener {
            val query = binding.homeSearchBar.text.toString()
            Toast.makeText(context, "Search for: $query", Toast.LENGTH_SHORT).show()
        }

        binding.homeFilterButton.setOnClickListener {
            val query = binding.homeSearchBar.text.toString()
            Toast.makeText(context, "Filter results of: $query", Toast.LENGTH_SHORT).show()
        }

        binding.homeCategoriesViewAllButton.setOnClickListener {
            Toast.makeText(context, "View All", Toast.LENGTH_SHORT).show()
        }

        binding.homePopularViewAllButton.setOnClickListener {
            Toast.makeText(context, "View All", Toast.LENGTH_SHORT).show()
        }

        binding.homeTrendingViewAllButton.setOnClickListener {
            Toast.makeText(context, "View All", Toast.LENGTH_SHORT).show()
        }

        //categories recycler View
        val categoriesAdapter = CategoriesAdapter(requireContext())
        binding.homeCategoriesList.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.homeCategoriesList.setAdapter(categoriesAdapter);

        //popular sellers recycler View
        val popularAdapter = PopularSellersAdapter(requireContext())
        binding.homePopularList.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.homePopularList.setAdapter(popularAdapter);

        //trending sellers recycler View
        val trendingAdapter = TrendingSellersAdapter(requireContext())
        binding.homeTrendingList.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.homeTrendingList.setAdapter(trendingAdapter);

        //Rest API Requests:

        //update user profile
        viewModel.getUserProfile()

        viewModel.getUserProfileObserver().observe(viewLifecycleOwner) {
            userProfile ->

            when(userProfile.code){
                ResponseModel.SUCCESSFUL_OPERATION -> {
                    if(userProfile.user != null) {
                        binding.homeGreetings.text = "Hello, ${userProfile.user.name}"

                        val address = (userProfile.user.addresses.get(0).address?:"") + userProfile.user?.addresses?.get(0)?.street + " Street"
                        binding.homeAddress.text = address

                        viewModel.setCachedUserData(userProfile.user)
                    }
                }
                ResponseModel.FAILED_AUTH -> {
                    Toast.makeText(context, "Session Expired", Toast.LENGTH_LONG).show()

                    viewModel.signOut()
                    navController.navigate(
                        R.id.action_home_to_signIn,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.home, true).build()
                    )
                }
                else -> {
                    Toast.makeText(context, "Error getting data form server !", Toast.LENGTH_LONG).show()
                }
            }

        }

        //get categories
        viewModel.getCategories()

        viewModel.getCategoriesObserver().observe(viewLifecycleOwner) {
            data ->

            when(data.code){
                ResponseModel.SUCCESSFUL_OPERATION -> {
                    if(data.categories != null) categoriesAdapter.addData(data.categories)
                    binding.HomeCategoriesLoading.visibility = View.GONE
                }
                ResponseModel.FAILED_AUTH -> {
                    Toast.makeText(context, "Session Expired", Toast.LENGTH_LONG).show()

                    viewModel.signOut()
                    navController.navigate(
                        R.id.action_home_to_signIn,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.home, true).build()
                    )
                }
                else -> {
                    Toast.makeText(context, "Error getting data form server !", Toast.LENGTH_LONG).show()
                }
            }

        }

        //get popular sellers
        viewModel.getPopularSellers(1)

        viewModel.getPopularSellersObserver().observe(viewLifecycleOwner) {
            data ->

            when(data.code){
                ResponseModel.SUCCESSFUL_OPERATION -> {
                    if(data.sellers != null) popularAdapter.addData(data.sellers)
                    binding.HomePopularLoading.visibility = View.GONE
                }
                ResponseModel.FAILED_AUTH -> {
                    Toast.makeText(context, "Session Expired", Toast.LENGTH_LONG).show()

                    viewModel.signOut()
                    navController.navigate(
                        R.id.action_home_to_signIn,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.home, true).build()
                    )
                }
                else -> {
                    Toast.makeText(context, "Error getting data form server !", Toast.LENGTH_LONG).show()
                }
            }

        }

        //get trending sellers
        viewModel.getTrendingSellers(1)

        viewModel.getTrendingSellersObserver().observe(viewLifecycleOwner) {
            data ->

            when(data.code){
                ResponseModel.SUCCESSFUL_OPERATION -> {
                    if(data.sellers != null) trendingAdapter.addData(data.sellers)
                    binding.HomeTrendingLoading.visibility = View.GONE
                }
                ResponseModel.FAILED_AUTH -> {
                    Toast.makeText(context, "Session Expired", Toast.LENGTH_LONG).show()

                    viewModel.signOut()
                    navController.navigate(
                        R.id.action_home_to_signIn,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.home, true).build()
                    )
                }
                else -> {
                    Toast.makeText(context, "Error getting data form server !", Toast.LENGTH_LONG).show()
                }
            }

        }


    }

}