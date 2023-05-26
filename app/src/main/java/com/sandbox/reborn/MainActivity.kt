package com.sandbox.reborn

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.site.api.model.SearchStatus
import com.huawei.hms.site.api.model.Site
import com.huawei.hms.site.widget.SearchFilter
import com.huawei.hms.site.widget.SearchFragment
import com.huawei.hms.site.widget.SiteSelectionListener
import com.sandbox.reborn.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Place Autocomplete
        val fragment = (requireNotNull(
            supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment)
        ) as SearchFragment)

        val encodedKey = URLEncoder.encode(HUAWEI_APP_KEY, "UTF-8")
        fragment.setApiKey(encodedKey)

        fragment.setSearchFilter(SearchFilter().apply {
            countryCode = "PL"
        })

        fragment.setOnSiteSelectedListener(object : SiteSelectionListener {
            override fun onSiteSelected(data: Site) {
                Toast.makeText(application, data.name, Toast.LENGTH_LONG).show()
            }

            override fun onError(status: SearchStatus) {
                Toast
                    .makeText(
                        application, status.errorCode + "\n" + status.errorMessage,
                        Toast.LENGTH_LONG
                    )
                    .show()
            }
        })
    }
}

private const val HUAWEI_APP_KEY: String = "<app key>"