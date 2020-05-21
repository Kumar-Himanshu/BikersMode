package com.bikersmode.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bikersmode.R
import com.bikersmode.database.UsersDBHelper
import com.bikersmode.interfaces.ICallPhone

/**
 * A fragment representing a list of Items.
 */
class CallLogListFragment : Fragment(), ICallPhone {
    override fun callPhone(mobileNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$mobileNumber")
        startActivity(callIntent)
    }

    // TODO: Customize parameters
    private var columnCount = 1


    lateinit var usersDBHelper: UsersDBHelper
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mAdapter: MyCallLogRecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        usersDBHelper = UsersDBHelper(context!!)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calllog_list, container, false)
        mRecyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = MyCallLogRecyclerViewAdapter(usersDBHelper.readAllUsers(), this)
        mRecyclerView!!.adapter = mAdapter!!
        return view
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                CallLogListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
