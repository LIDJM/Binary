package com.example.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import android.util.Log
import android.content.Intent
import java.io.IOException
import java.util.ArrayList
import org.json.JSONArray
import org.json.JSONException

class ListActivity : AppCompatActivity() {

    private lateinit var mSites: ArrayList<Site>
    private lateinit var mAdapter: SiteAdapter
    private lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recyclerView)
        setupRecyclerView()
        initDataFromFile()
        //        mSites = createMockSites()
    }


    private fun setupRecyclerView() {
        mSites = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = SiteAdapter(mSites, this) { site ->
            contactOnClick(site)
        }

        recycler.adapter = mAdapter
    }

    /* RecyclerView item is clicked. */
    private fun contactOnClick(site: Site) {
        Log.d(TAG, "Click on: $site")
        site?.let {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(site: Site) {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }


    /**
     * Generates mock contact data to populate the UI from a JSON file in the
     * assets directory, called from the options menu.
     */
    private fun initDataFromFile() {
        val sitesString = readSiteJsonFile()
        try {
            val sitesJson = JSONArray(sitesString)
            for (i in 0 until sitesJson.length()) {
                val siteJson = sitesJson.getJSONObject(i)
                val site = Site(
                    siteJson.getString("nombre"),
                    siteJson.getString("descripcion"),
                    siteJson.getString("puntuacion"),
                    siteJson.getString("imageUrl")
                )
                Log.d(TAG, "generateSites: $site")
                mSites.add(site)

            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readSiteJsonFile(): String? {
        var sitesString: String? = null
        try {
            val inputStream = assets.open("sites.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            sitesString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sitesString
    }

    companion object {
        private val TAG = ListActivity::class.java.simpleName
    }
}
