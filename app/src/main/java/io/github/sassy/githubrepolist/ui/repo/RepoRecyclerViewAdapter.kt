package io.github.sassy.githubrepolist.ui.repo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import io.github.sassy.githubrepolist.R
import io.github.sassy.githubrepolist.databinding.FragmentRepoBinding


import io.github.sassy.githubrepolist.ui.repo.RepoFragment.OnListFragmentInteractionListener

class RepoRecyclerViewAdapter(
    private val viewModel: RepoViewModel,
    private val mListener: OnListFragmentInteractionListener?,
    private val parentLifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<RepoRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val position = v.tag.toString().toInt()

            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(null)

            val bundle = bundleOf("repositoryName" to viewModel.reposFullNames.value!![position])
            Navigation.findNavController(v).navigate(R.id.detailFragment, bundle)
        }

        // これ必要？ないと更新されないが。
        viewModel.reposFullNames.observe(parentLifecycleOwner, Observer { list ->
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: FragmentRepoBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.fragment_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.position = position
        holder.binding.lifecycleOwner = parentLifecycleOwner
        holder.binding.root.tag = position

        val view = holder.binding.root
        with(view) {
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int {
        return viewModel.getCount()
    }

    inner class ViewHolder(val binding: FragmentRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        val mContentView: TextView = binding.root.rootView.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
