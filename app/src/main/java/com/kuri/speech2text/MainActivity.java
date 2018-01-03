package com.kuri.speech2text;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextInputLayout voiceLayerInput;
    SpeechRecognizer mSpeechRecognizer;
    ImageButton micButton;


    //NOTES ON USING GOOGLE'S SPEECH RECOGNIZER:

    //Create a Speech Recognizer using SpeechRecognizer.createSpeechRecognizer(this);
    //Make an intent and pass in the necessary information regarding the language input
    //When the button/prompt is made, invoke startListening() and pass in the intent
    //In mSpeechRecognizer.setRecognitionListener(new RecognitionListener() -->
    //override the onResults(), get the result in an array list and display the first match

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        voiceLayerInput = (TextInputLayout) findViewById(R.id.voice_input_layout);
        micButton = (ImageButton) findViewById(R.id.mic_button);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        /*

        EXTRA_LANGUAGE_MODEL
        Informs the recognizer which speech model to prefer when performing ACTION_RECOGNIZE_SPEECH.
        The recognizer uses this information to fine tune the results. This extra is required.
        Activities implementing ACTION_RECOGNIZE_SPEECH may interpret the values as they see fit


        LANGUAGE_MODEL_FREE_FORM
        Use a language model based on free-form speech recognition.
        This is a value to use for EXTRA_LANGUAGE_MODEL.



        EXTRA_LANGUAGE
        Optional IETF language tag (as defined by BCP 47), for example "en-US".
        This tag informs the recognizer to perform speech recognition in a language different
        than the one set in the getDefault().

         */


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                voiceLayerInput.getEditText().setHint("Listening...");
            }
        });


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {


                //get all matches
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //display first match
                if (matches != null)
                    voiceLayerInput.getEditText().setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }


            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }



}
