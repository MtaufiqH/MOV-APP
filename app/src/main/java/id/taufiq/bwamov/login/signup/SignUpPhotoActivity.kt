package id.taufiq.bwamov.login.signup

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import id.taufiq.bwamov.home.HomeActivity
import id.taufiq.bwamov.R
import id.taufiq.bwamov.utils.MyPreferences
import id.taufiq.bwamov.utils.showToast
import id.taufiq.bwamov.utils.startActivity
import kotlinx.android.synthetic.main.sign_up_photo_activity.*


class SignUpPhotoActivity : AppCompatActivity() {


    private lateinit var storageRefs: StorageReference
    private lateinit var prefs: MyPreferences

    companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000

        //Permission code
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_photo_activity)

        prefs = MyPreferences(this)

        storageRefs = FirebaseStorage.getInstance().reference


        val nameUser = intent.getStringExtra("NAMA")
        val introduce = "Selamat datang,\n$nameUser"
        tv_intro_name.text = introduce

        btn_simpan_lanjut.setOnClickListener { startActivity(HomeActivity::class.java) }
        btn_upload_nanti.setOnClickListener { startActivity(HomeActivity::class.java) }

        btn_upload_image.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    // permission granted
                    pickImage()
                }

            } else {
                // system OS < Marshmallow
                pickImage()
            }
        }


        btn_delete_image.setOnClickListener {

        }
    }

    private fun pickImage() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        val mimeType = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                } else {
                    showToast("Permission denied!")
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            IMAGE_PICK_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let { uri ->
                        launchImageCrop(uri)
                    }

                    btn_simpan_lanjut.visibility = View.VISIBLE
                    btn_upload_image.visibility = View.GONE
                    btn_delete_image.visibility = View.VISIBLE
                }


            }

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    result.uri?.let { imageUri ->
                        setImage(imageUri)

                    }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Log.e("TAG", "onActivityResult: ${result.error}")
                }
            }


        }


    }


    private fun launchImageCrop(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(this)
    }


    private fun setImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .circleCrop()
            .into(iv_photo_profil)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        showToast("buru-buru? Upload nanti aja")
    }

}