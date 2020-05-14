package io.github.sassy.githubrepolist.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection

import io.github.sassy.githubrepolist.R
import io.github.sassy.githubrepolist.ViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View = inflater.inflate(R.layout.login_fragment, container, false)
        val button: Button = view.findViewById(R.id.login_button)
        button.setOnClickListener({v ->
            val edit: EditText = view.findViewById(R.id.login_edittext)
            if (edit.text != null) {
                viewModel.setLogin(edit.text.toString())
                findNavController().navigate(R.id.mainFragment)
            }
        })
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context)
    }
}
