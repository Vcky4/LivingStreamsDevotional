package com.ghor.livingstreamsdevotional.ui.devotional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DevotionalViewModel : ViewModel() {
    //private
    private val _qualifierText = MutableLiveData<String>("Light House Fellowship Edition")
    private val _dateText = MutableLiveData<String>("Saturday 01 May")
    private val _topicText = MutableLiveData<String>("wisdom will bring success")
    private val _bodyText = MutableLiveData<String>("Luke 1:5-9,11,13 KJV\n" +
            "“There was in the days of Herod, the king of Judaea, a certain priest named Zacharias, of the course of Abia: and his wife was of the daughters of Aaron, and her name was Elisabeth. And they were both righteous before God, walking in all the commandments and ordinances of the Lord blameless. And they had no child, because that Elisabeth was barren, and they both were now well stricken in years. And it came to pass, that while he executed the priest's office before God in the order of his course, According to the custom of the priest's office, his lot was to burn incense when he went into the temple of the Lord. And there appeared unto him an angel of the Lord standing on the right side of the altar of incense. But the angel said unto him, Fear not, Zacharias: for thy prayer is heard; and thy wife Elisabeth shall bear thee a son, and thou shalt call his name John”.\n" +
            "\n" +
            "Dedication is not always an easy road to travel. When a man decides to be dedicated to God there would be tests, trials and temptations on his path, all to knock him off his dedication to God. When challenges come, they get to attack your dedication to God, they come to separate you from your commitment, dedication and loyalty to God. The reason is there are very strong obvious rewards for service that cannot be denied nor disputed. Firstly, God said He is not unrighteous to forget your labour of love that you have showed towards his name, in that you have ministered to the saints and still minister. If God said He will not forget your labour of love He means exactly just that.\n" +
            "\n" +
            "Another reason why satan will do anything to take you away from the path of service is the massive blessings that God has promised to those who dedicate themselves to service. “And ye shall serve the Lord your God, and he shall bless thy bread, and thy water; and I will take sickness away from the midst of thee. There shall nothing cast their young, nor be barren, in thy land: the number of thy days I will fulfil. I will send my fear before thee, and will destroy all the people to whom thou shalt come, and I will make all thine enemies turn their backs unto thee”. (Exodus 23:25-27 KJV). The blessings are as follows: you will be above food poisoning, sickness will leave you, you will never suffer miscarriages, and neither you nor anything connected to you can be barren. Further more you will live long, etc.\n" +
            "\n" +
            "These were some of the reasons why satan targeted Zechariah and Elizabeth. But Zechariah was a dedicated man, he was loyal and committed to God as far as service was concerned. No amount of delay or frustration would remove Zechariah from his commitment. He had arrived old age already and his wife had passed child bearing age already, but he would not give up his life of service to God. His lot was to burn incense in the temple while people waited and prayed outside. He never left his duty post for once, and one day, right at the place of service, God visited him. Let me encourage you today to never leave your place of service, never abandon your duty post no matter what you see. The place of service is your place of blessing, and no matter how long it takes, if you stay at your duty post, your blessings will come through and the delay will be broken. Receive grace to dedicate your life to the service of God in Jesus Name.\n" +
            "\n" +
            " *Action Points* \n" +
            "i. Identify two to three areas of service you want to dedicate yourself to.\n" +
            "ii. Doggedly and ruggedly dedicate yourself to those points of service.\n" +
            "iii. Trust God to come through for you as you stand in the place of service.\n" +
            "\n" +
            " *Prayer* \n" +
            "-Lord please baptize me with the grace and power to stand firm in dedication to your service in Jesus Name.\n" +
            "-Lord help me to wait for you for as long as necessary in the place of service till you come through for me in Jesus Name.\n"
            )
    private val _nuggetText = MutableLiveData<String>("The place of service is your place of blessing, and no matter how long it takes, if you stay at your duty post, your blessings will come through and the delay will be broken." +
            " _For Further Reading and Meditation_ \\n\" +\n" +
            "            \"MORNING\\n\" +\n" +
            "            \"Ezekiel 9, Luke 21\\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"EVENING\\n\" +\n" +
            "            \"Psalm 31\\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \"\\n\" +\n" +
            "            \" _For Feedback & Enquires, please send a mail to *ezekiel.atang@yahoo.com*_\"")

    //public
    val qualifierText: LiveData<String> = _qualifierText
    val dateText: LiveData<String> = _dateText
    val topicText: LiveData<String> = _topicText
    val bodyText: LiveData<String> = _bodyText
    val nuggetText: LiveData<String> = _nuggetText

    //methods

}