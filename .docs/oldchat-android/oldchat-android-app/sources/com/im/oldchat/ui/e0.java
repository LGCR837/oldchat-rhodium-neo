package com.im.oldchat.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.ui.c0;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class e0 extends f0.a implements c0.d {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public String f4496A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public String f4497B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f4498C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public String f4499D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public String f4500E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public View f4501F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public View f4502G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public View f4503H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public ProgressBar f4504I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TextView f4505J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public TextView f4506K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public EditText f4507L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public View f4508M;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListView f4514w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ProgressBar f4515x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public c0 f4516y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final List f4517z = new ArrayList();

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f4509N = 0;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f4510O = true;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f4511P = false;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f4512Q = false;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public String f4513R = "";

    public static String h0(long j2) {
        if (j2 <= 0) {
            return "0B";
        }
        double d2 = j2;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        double d4 = d3 / 1024.0d;
        double d5 = d4 / 1024.0d;
        return d5 >= 1.0d ? String.format(Locale.US, "%.2fGB", Double.valueOf(d5)) : d4 >= 1.0d ? String.format(Locale.US, "%.2fMB", Double.valueOf(d4)) : d3 >= 1.0d ? String.format(Locale.US, "%.2fKB", Double.valueOf(d3)) : String.format(Locale.US, "%dB", Long.valueOf(j2));
    }

    public boolean f0(j0.t tVar) {
        String str;
        if (tVar == null || (str = this.f4497B) == null || str.isEmpty()) {
            return false;
        }
        if (this.f4497B.equals(tVar.f5623f)) {
            return true;
        }
        String str2 = this.f4500E;
        return str2 != null && this.f4497B.equals(str2);
    }

    public String g0(long j2) {
        if (j2 < 1024) {
            return j2 + "B";
        }
        long j3 = j2 / 1024;
        if (j3 < 1024) {
            return j3 + "KB";
        }
        return (j3 / 1024) + "." + (((j3 % 1024) * 10) / 1024) + "MB";
    }

    public String i0(long j2, long j3) {
        if (j3 <= 0) {
            return g0(j2);
        }
        return g0(j2) + "/" + g0(j3);
    }

    public String j0(long j2) {
        if (j2 <= 0) {
            return "";
        }
        long j3 = j2 / 1024;
        if (j3 < 1024) {
            return j3 + "KB/s";
        }
        return (j3 / 1024) + "." + (((j3 % 1024) * 10) / 1024) + "MB/s";
    }

    public String k0(String str) {
        return o0.A.g(str);
    }

    public void l0(boolean z2) {
        ProgressBar progressBar = this.f4515x;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
    }
}
