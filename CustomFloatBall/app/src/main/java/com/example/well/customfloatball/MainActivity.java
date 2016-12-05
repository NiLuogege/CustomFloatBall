package com.example.well.customfloatball;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chickOverlay();
        init();
    }

    private void chickOverlay() {
        if (Build.VERSION.SDK_INT >= 23) {//打开设置页面让用户手动设置
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
                Toast.makeText(this, "请先允许FloatBall出现在顶部", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init() {
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {//开启帮助服务
            @Override
            public void onClick(View view) {
                checkAccessibility();
                Intent intent = new Intent(MainActivity.this, FloatBallService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", FloatBallService.TYPE_ADD);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        findViewById(R.id.btn_quit).setOnClickListener(new View.OnClickListener() {//关闭帮助服务
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FloatBallService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", FloatBallService.TYPE_DEL);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

    }

    private void checkAccessibility() {
        if (!AccessibilityUtils.isAccessibilitySettingsOn(this)) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "请先开启FloatBall辅助功能", Toast.LENGTH_SHORT).show();
        }
    }
}
