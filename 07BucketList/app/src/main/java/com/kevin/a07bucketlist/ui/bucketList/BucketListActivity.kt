package com.kevin.a07bucketlist.ui.bucketList

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import com.kevin.a07bucketlist.R
import com.kevin.a07bucketlist.base.BaseActivity
import com.kevin.a07bucketlist.databinding.ActivityBucketListBinding
import com.kevin.a07bucketlist.util.CustomDividerItemDecoration
import com.kevin.a07bucketlist.util.SnackBarHelper
import kotlinx.android.synthetic.main.activity_bucket_list.*
import org.jetbrains.anko.dimen

class BucketListActivity : BaseActivity<ActivityBucketListBinding, BucketListViewModel>() {

	private val bucketListAdapter = BucketListAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		rvBucketList.adapter = bucketListAdapter
		rvBucketList.addItemDecoration(CustomDividerItemDecoration(this))

		viewModel.bucketList.observe(this, Observer {
			bucketListAdapter.updateList(it)
			SnackBarHelper.showSnackBarMessage(this@BucketListActivity, "Bucket List Update")
		})
		fabAddBucketItem.setOnClickListener { SnackBarHelper.showSnackBarMessage(this@BucketListActivity, "Add New Bucket List Item") }
	}

	override fun getLayoutId(): Int = R.layout.activity_bucket_list

	override fun initViewModelBinding() {
		binding.viewModel = viewModel
	}

	override fun getVMClass(): Class<BucketListViewModel> = BucketListViewModel::class.java

	override fun getActivityTitle(): String = "Bucket List"
}