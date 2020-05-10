package io.github.sassy.githubrepolist.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import io.github.sassy.githubrepolist.R
import io.github.sassy.githubrepolist.databinding.FragmentRepoBinding


import io.github.sassy.githubrepolist.ui.RepoFragment.OnListFragmentInteractionListener
import io.github.sassy.githubrepolist.vo.Repo

import kotlinx.android.synthetic.main.fragment_repo.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class RepoRecyclerViewAdapter(
    private val viewModel: RepoViewModel,
    private val mListener: OnListFragmentInteractionListener?,
    private val parentLifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<RepoRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as String
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
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

        val view = holder.binding.root
        with(view) {
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int {
        val value = viewModel.repos.value
        if (value == null) {
            return 0
        }
        return value.size
    }

    inner class ViewHolder(val binding: FragmentRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        val mContentView: TextView = binding.root.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
