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
                trocaFragment(getString(R.string.husky))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_hound -> {
                trocaFragment(getString(R.string.hound))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_labrador -> {
                trocaFragment(getString(R.string.labrador))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_pug -> {
                trocaFragment(getString(R.string.pug))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun trocaFragment(texto: String) {
        val ft = supportFragmentManager.beginTransaction()
        val fragment =
            ImageListFragment.newInstance(texto.toLowerCase())
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

        trocaFragment(getString(R.string.husky))
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

}
