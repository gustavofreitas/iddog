package com.example.iddog.ui.image


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.iddog.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_image_list.*

class ImageListActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_husky -> {
                cahngeFragment(getString(R.string.husky))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_hound -> {
                cahngeFragment(getString(R.string.hound))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_labrador -> {
                cahngeFragment(getString(R.string.labrador))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_pug -> {
                cahngeFragment(getString(R.string.pug))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun cahngeFragment(category: String) {
        val ft = supportFragmentManager.beginTransaction()
        val fragment =
            ImageListFragment.newInstance(category.toLowerCase())
        ft.replace(R.id.flContainer, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        cahngeFragment(getString(R.string.husky))
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

}
