package io.github.sassy.githubrepolist.ui.repo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection
import io.github.sassy.githubrepolist.R
import io.github.sassy.githubrepolist.ViewModelFactory
import io.github.sassy.githubrepolist.databinding.FragmentRepoListBinding
import io.github.sassy.githubrepolist.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.view.*

import javax.inject.Inject



/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RepoFragment.OnListFragmentInteractionListener] interface.
 */
class RepoFragment : Fragment(), TextWatcher {

    private var columnCount = 1

    private lateinit var binding: FragmentRepoListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: RepoViewModel by viewModels {
        viewModelFactory
    }

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val view: View = binding.root.rootView.list
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    RepoRecyclerViewAdapter(
                        viewModel,
                        listener,
                        viewLifecycleOwner
                    )
            }
        }

        val edit : EditText = binding.root.findViewById(R.id.serchText)
        edit.addTextChangedListener(this)

        viewModel.fetchRepos()

        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: String?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RepoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun afterTextChanged(s: Editable?) {
        viewModel.searchRequest(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // do nothing
    }
}
