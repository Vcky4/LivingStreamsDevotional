package com.ghor.livingstreamsdevotional.ui.devotional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DevotionalViewModel : ViewModel() {
    //private
    private val database: DatabaseReference = Firebase.database.reference
    private val _qualifierText = MutableLiveData<String>("Light House Fellowship Edition")
    private val _dateText = MutableLiveData<String>("Saturday 01 May")
    private val _topicText = MutableLiveData<String>("wisdom will bring success")
    private val _morning = MutableLiveData<String>("Ezekiel 9, Luke 21")
    private val _evening = MutableLiveData<String>("Psalm 31")
    private val _prayers = MutableLiveData<String>("-Lord please baptize me with the grace and power to stand firm in dedication to your service in Jesus Name.\n" +
            "-Lord help me to wait for you for as long as necessary in the place of service till you come through for me in Jesus Name.\n"
    )
    private val _actionPoint = MutableLiveData<String>("i. Identify two to three areas of service you want to dedicate yourself to.\n" +
            "ii. Doggedly and ruggedly dedicate yourself to those points of service.\n" +
            "iii. Trust God to come through for you as you stand in the place of service.\n")
    private val _scripture = MutableLiveData<String>("Luke 1:5-9,11,13 KJV")
    private val _scriptureBody = MutableLiveData<String>("There was in the days of Herod, the king of Judaea, a certain priest named Zacharias, of the course of Abia: and his wife was of the daughters of Aaron, and her name was Elisabeth. And they were both righteous before God, walking in all the commandments and ordinances of the Lord blameless. And they had no child, because that Elisabeth was barren, and they both were now well stricken in years. And it came to pass, that while he executed the priest's office before God in the order of his course, According to the custom of the priest's office, his lot was to burn incense when he went into the temple of the Lord. And there appeared unto him an angel of the Lord standing on the right side of the altar of incense. But the angel said unto him, Fear not, Zacharias: for thy prayer is heard; and thy wife Elisabeth shall bear thee a son, and thou shalt call his name John")
    private val _bodyText = MutableLiveData<String>()
    private val _nuggetText = MutableLiveData<String>("The place of service is your place of blessing, and no matter how long it takes, if you stay at your duty post, your blessings will come through and the delay will be broken.")

    //public
    val qualifierText: LiveData<String> = _qualifierText
    val dateText: LiveData<String> = _dateText
    val topicText: LiveData<String> = _topicText
    val bodyText: LiveData<String> = _bodyText
    val nuggetText: LiveData<String> = _nuggetText
    val scripture: LiveData<String> = _scripture
    val scriptureBody: LiveData<String> = _scriptureBody
    val prayers: LiveData<String> = _prayers
    val actionPoint: LiveData<String> = _actionPoint
    val morning: LiveData<String> = _morning
    val evening: LiveData<String> = _evening

    //methods


    fun update(){
        val ref = database.child("devotional").child("body").ref
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
               _bodyText.value = snapshot.value.toString()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}

        }

        ref.addChildEventListener(childEventListener)

    }

}