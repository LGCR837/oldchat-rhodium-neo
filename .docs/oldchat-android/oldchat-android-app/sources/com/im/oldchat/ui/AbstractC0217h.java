package com.im.oldchat.ui;

import android.R;
import android.os.Build;
import android.text.Editable;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: renamed from: com.im.oldchat.ui.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0217h {

    /* JADX INFO: renamed from: com.im.oldchat.ui.h$a */
    public class a implements TextView.OnEditorActionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f4595a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f4596b;

        public a(EditText editText, d dVar) {
            this.f4595a = editText;
            this.f4596b = dVar;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (keyEvent != null) {
                return false;
            }
            if ((i2 != 4 && i2 != 6) || !AbstractC0217h.l(this.f4595a)) {
                return false;
            }
            d dVar = this.f4596b;
            if (dVar == null) {
                return true;
            }
            dVar.a();
            return true;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.h$b */
    public class b implements View.OnKeyListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f4597a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f4598b;

        public b(EditText editText, d dVar) {
            this.f4597a = editText;
            this.f4598b = dVar;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            if (i2 != 66 || keyEvent == null || keyEvent.getAction() != 1 || !AbstractC0217h.l(this.f4597a)) {
                return false;
            }
            d dVar = this.f4598b;
            if (dVar != null) {
                dVar.a();
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.h$c */
    public class c implements ActionMode.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f4599a;

        public c(EditText editText) {
            this.f4599a = editText;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem == null || menuItem.getItemId() != 9301) {
                EditText editText = this.f4599a;
                return editText != null && editText.onTextContextMenuItem(menuItem.getItemId());
            }
            AbstractC0217h.k(this.f4599a);
            actionMode.finish();
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            AbstractC0217h.d(menu);
            menu.add(0, 9301, 0, "换行");
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.h$d */
    public interface d {
        void a();
    }

    public static void c(ContextMenu contextMenu) {
        contextMenu.add(0, R.id.cut, 0, "剪切").setEnabled(true);
        contextMenu.add(0, R.id.copy, 1, "复制").setEnabled(true);
        contextMenu.add(0, R.id.paste, 2, "粘贴").setEnabled(true);
        contextMenu.add(0, R.id.selectAll, 3, "全选").setEnabled(true);
    }

    public static void d(Menu menu) {
        h(menu, R.id.cut, "剪切");
        h(menu, R.id.copy, "复制");
        h(menu, R.id.paste, "粘贴");
        h(menu, R.id.selectAll, "全选");
    }

    public static void e(EditText editText) {
        if (editText == null) {
            return;
        }
        int imeOptions = editText.getImeOptions() & (-1073742080);
        editText.setImeOptions(l(editText) ? imeOptions | 4 : imeOptions | 1073741825);
    }

    public static void f(EditText editText, d dVar) {
        if (editText == null) {
            return;
        }
        e(editText);
        editText.setOnEditorActionListener(new a(editText, dVar));
        editText.setOnKeyListener(new b(editText, dVar));
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 11) {
            editText.setCustomSelectionActionModeCallback(g(editText));
        }
        if (i2 >= 23) {
            editText.setCustomInsertionActionModeCallback(g(editText));
        }
    }

    public static ActionMode.Callback g(EditText editText) {
        return new c(editText);
    }

    public static void h(Menu menu, int i2, String str) {
        if (menu == null) {
            return;
        }
        MenuItem menuItemFindItem = menu.findItem(i2);
        if (menuItemFindItem == null) {
            menuItemFindItem = menu.add(0, i2, 0, str);
        }
        menuItemFindItem.setVisible(true);
        menuItemFindItem.setEnabled(true);
        menuItemFindItem.setShowAsAction(0);
    }

    public static void i(ContextMenu contextMenu) {
        if (contextMenu == null) {
            return;
        }
        c(contextMenu);
        contextMenu.add(0, 9301, 0, "换行");
    }

    public static boolean j(EditText editText, MenuItem menuItem) {
        int itemId;
        if (menuItem == null || menuItem.getItemId() != 9301) {
            return menuItem != null && ((itemId = menuItem.getItemId()) == 16908320 || itemId == 16908321 || itemId == 16908322 || itemId == 16908319) && editText != null && editText.onTextContextMenuItem(itemId);
        }
        k(editText);
        return true;
    }

    public static void k(EditText editText) {
        Editable text;
        if (editText == null || (text = editText.getText()) == null) {
            return;
        }
        int selectionStart = editText.getSelectionStart();
        int selectionEnd = editText.getSelectionEnd();
        if (selectionStart < 0) {
            selectionStart = text.length();
        }
        if (selectionEnd < 0) {
            selectionEnd = text.length();
        }
        if (selectionStart > selectionEnd) {
            int i2 = selectionEnd;
            selectionEnd = selectionStart;
            selectionStart = i2;
        }
        text.replace(selectionStart, selectionEnd, "\n");
        int length = selectionStart + 1;
        if (length > text.length()) {
            length = text.length();
        }
        editText.setSelection(length);
    }

    public static boolean l(EditText editText) {
        return editText != null && o0.V.h(editText.getContext());
    }
}
