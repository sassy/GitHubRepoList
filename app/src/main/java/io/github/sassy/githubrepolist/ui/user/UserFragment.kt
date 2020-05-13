package io.github.sassy.githubrepolist.ui.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection

import io.github.sassy.githubrepolist.R
import io.github.sassy.githubrepolist.ViewModelFactory
import io.github.sassy.githubrepolist.databinding.UserFragmentBinding
import javax.inject.Inject

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var binding: UserFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: UserViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, Observer {user ->
            val imageView: ImageView = binding.root.findViewById(R.id.imageView)
            Picasso.get().load(user.avatarUrl).into(imageView)
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context)
    }

}
