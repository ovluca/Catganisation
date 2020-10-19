package com.shop.catganisation.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shop.catganisation.R
import com.shop.catganisation.databinding.LoginFragmentBinding
import com.shop.catganisation.viewmodel.LoginViewModel
import javax.inject.Inject

/**
 * Created by Ovidiu Florin Luca on 19/10/2020.
 */
class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            run {
                binding.usernameInputLayout.isErrorEnabled = false
                binding.passwordInputLayout.isErrorEnabled = false
                if (binding.usernameInputText.text.isNullOrBlank()) {
                    binding.usernameInputLayout.error = getString(R.string.required)
                    return@run
                }

                if (binding.passwordInputText.text.isNullOrBlank()) {
                    binding.passwordInputLayout.error = getString(R.string.required)
                    return@run
                }

                login()

            }
        }
    }

    private fun login() {
        binding.loginProgressBar.visibility = View.VISIBLE
        binding.contentWrapper.visibility = View.GONE

        viewModel.login(
            binding.passwordInputText.text.toString(),
            binding.passwordInputText.text.toString()
        ) { isError ->
            if (isError) {
                Toast.makeText(requireContext(), "Login Failed!", Toast.LENGTH_SHORT).show()
                binding.loginProgressBar.visibility = View.GONE
                binding.contentWrapper.visibility = View.VISIBLE
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_splashFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}