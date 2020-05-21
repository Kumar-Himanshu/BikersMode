package com.bikersmode.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bikersmode.R
import com.bikersmode.database.UserModel


import com.bikersmode.interfaces.ICallPhone

import kotlinx.android.synthetic.main.fragment_calllog.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyCallLogRecyclerViewAdapter(
        private val mValues: ArrayList<UserModel>,private val callPhone: ICallPhone)
    : RecyclerView.Adapter<MyCallLogRecyclerViewAdapter.ViewHolder>() {
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
        holder.mParentLL.setOnClickListener {
            callPhone.callPhone(item.mobileNumber)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.user_name
        val mContentView: TextView = mView.user_Mobile
        val mTimeView: TextView = mView.call_time
        val mParentLL:LinearLayout = mView.parentLL

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
