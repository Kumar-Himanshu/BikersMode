package com.bikersmode.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bikersmode.R
import com.bikersmode.database.UserModel


import com.bikersmode.fragments.CallLogListFragment.OnListFragmentInteractionListener
import com.bikersmode.fragments.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_calllog.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCallLogRecyclerViewAdapter(
        private val mValues: ArrayList<UserModel>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyCallLogRecyclerViewAdapter.ViewHolder>() {

//    private val mOnClickListener: View.OnClickListener
//
//    init {
//        mOnClickListener = View.OnClickListener { v ->
//            val item = v.tag as DummyItem
//            // Notify the active callbacks interface (the activity, if the fragment is attached to
//            // one) that an item has been selected.
//            mListener?.onListFragmentInteraction(item)
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_calllog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.mobileNumber
        holder.mTimeView.text = item.time
//
//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.user_name
        val mContentView: TextView = mView.user_Mobile
        val mTimeView: TextView = mView.call_time

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
