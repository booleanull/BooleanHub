package com.boolenull.booleanhub.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.ui.fragment.BlogFragment
import com.boolenull.booleanhub.ui.fragment.EnumFragment
import com.boolenull.booleanhub.ui.fragment.EnumFragment.BLOG
import com.boolenull.booleanhub.ui.fragment.EnumFragment.REPOSITORY
import com.boolenull.booleanhub.ui.fragment.LinkFragment
import com.boolenull.booleanhub.ui.fragment.RepositoryFragment
import javax.inject.Inject

class PageAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    @Inject
    lateinit var context: Context

    init {
        MyApplication.applicationComponent.inject(this)
    }

    override fun getCount(): Int {
        return EnumFragment.values().size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            BLOG.ordinal -> {
                BlogFragment()
            }
            REPOSITORY.ordinal -> {
                RepositoryFragment()
            }
            else -> {
                LinkFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            BLOG.ordinal -> {
                context.getString(R.string.blog)
            }
            REPOSITORY.ordinal -> {
                context.getString(R.string.repository)
            }
            else -> {
                context.getString(R.string.aboutme)
            }
        }
    }


}