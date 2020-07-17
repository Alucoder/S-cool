package com.aryan.scool.Dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aryan.scool.R;

public class Dialog_Teaceher extends AppCompatDialogFragment {

    TextView tv_name, tv_phone, tv_email;
    ImageView profile, phone, email;

    Context pContext;
    Activity pActivity;
    private static final int REQUEST_CALL = 1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_teachersinfo, null);

        Bundle mArgs = getArguments();
//        String teachername = mArgs.getString("name");
//        String teacheremail = mArgs.getString("phone");
//        long teacherphone = mArgs.getLong("email");

        builder.setView(view)
                .setTitle("Teacher Information")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        tv_name = view.findViewById(R.id.tv_teacher_name);
        tv_phone = view.findViewById(R.id.teacher_phone);
        tv_email = view.findViewById(R.id.tv_teacher_email);

        profile = view.findViewById(R.id.img_dialog_profile);
        phone = view.findViewById(R.id.img_call_teacher);
        email = view.findViewById(R.id.img_email_teacher);


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno = tv_phone.getText().toString();
                if(phno.trim().length() > 0) {
                    if(ContextCompat.checkSelfPermission(pContext, Manifest.permission.CALL_PHONE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions( pActivity,
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    } else {
                        String dial = "tel:" + phno;
                        pContext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }
                } else {
                    Toast.makeText( pContext, "No phone number available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL  , tv_email.getText());
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Dear " );
                    emailIntent.putExtra(Intent.EXTRA_TEXT   , "Enter your message");

                    emailIntent.setType("text/plain");
                    startActivity(emailIntent);
                } catch (Exception e){

                }
            }
        });

        return builder.create();
    }

}
