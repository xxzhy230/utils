package com.yqjr.utils.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanghongyu on 2018/7/12.
 */

public class ForbidEditUtils {
    private static String str = "";

    /**
     * 过滤输入框输入emoji和特殊符号 并 控制长度
     *
     * @param et
     */
    public static void setEmojiFilter(EditText et, int lenght) {
        InputFilter emojiFilter = new InputFilter() {
            Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (matcher.find()) {
                    return "";
                }
                return null;
            }
        };
        et.setFilters(new InputFilter[]{emojiFilter, new InputFilter.LengthFilter(lenght)});
    }

    /**
     * 过滤输入框输入emoji
     * 控制长度
     *
     * @param et
     */
    public static void setEmojiFilterAndSymbol(EditText et, int lenght) {
        InputFilter emojiFilter = new InputFilter() {
            Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|[`~@#$%^&*()+=|{}''\\\\[\\\\]<>/~#￥%……&*（）——+|{}【】‘”“’，。‘；？！～·?,!.~`;/]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (matcher.find()) {
                    return "";
                }
                return null;
            }
        };
        et.setFilters(new InputFilter[]{emojiFilter, new InputFilter.LengthFilter(lenght)});
    }


    /**
     * 输入框小数的位数
     */
    public static int DECIMAL_DIGITS = 2;

    private static InputFilter lengthFilter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (dest.length() == 0 && source.equals(".")) {
                return "";
            }

            String dValue = dest.toString();
            if (source.equals(".")) {
                if (dValue.contains(".")) {
                    str = source.toString();
                } else {
                    str = "";
                }
                if (str.equals(".")) {
                    return "";
                }
            }
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                if (dotValue.length() == DECIMAL_DIGITS) {
                    return "";
                }
            }
            return null;
        }

    };


    private static InputFilter lengthFilterFour = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (dest.length() == 0 && source.equals(".")) {
                return "";
            }
            String dValue = dest.toString();
            if (source.equals(".")) {
                if (dValue.contains(".")) {
                    str = source.toString();
                } else {
                    str = "";
                }
                if (str.equals(".")) {
                    return "";
                }
            }
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                if (dotValue.length() == 4) {
                    return "";
                }
            }
            return null;
        }

    };
    private static InputFilter lengthFilterNoZero = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (dest.length() == 0 && source.equals(".")) {
                return "";
            }
            if (dest.length() == 0 && source.equals("0")) {
                return "";
            }
            String dValue = dest.toString();
            if (source.equals(".")) {
                if (dValue.contains(".")) {
                    str = source.toString();
                } else {
                    str = "";
                }
                if (str.equals(".")) {
                    return "";
                }
            }
            if (!dValue.contains(".")) {
                if (dValue.length() == 7) {
                    return "";
                }
            } else {
                String split = dValue.substring(0, dValue.indexOf("."));
                if (split.length() == 7) {
                    return "";
                }
            }
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                if (dotValue.length() == DECIMAL_DIGITS) {
                    return "";
                }
            }
            return null;
        }

    };

    private static InputFilter lengthFilterNoZeroOne = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (dest.length() == 0 && source.equals(".")) {
                return "";
            }
            if (dest.length() == 0 && source.equals("0")) {
                return "";
            }

            String dValue = dest.toString();
            if (source.equals(".")) {
                if (dValue.contains(".")) {
                    str = source.toString();
                } else {
                    str = "";
                }
                if (str.equals(".")) {
                    return "";
                }
            }


            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                if (dotValue.length() == 1) {
                    return "";
                }
            }
            return null;
        }
    };


    private static InputFilter lengthFilterTwo = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (dest.length() == 0 && source.equals(".")) {
                return "0.";
            }

            if (!source.toString().equals(".") && dest.toString().equals("0")) {
                return "";
            }
            String dValue = dest.toString();
            if (dValue.substring(dstart, dend).equals(".")) {

                String substring = dValue.replace(",", "").substring(dValue.replace(",", "").indexOf("."), dValue.replace(",", "").length());
                if (!TextUtils.isEmpty(substring) && !substring.equals(".")) {
                    if (source.equals("")) {
                        return ".";
                    }
                }
            }
            if (source.equals(".")) {
                if (dValue.contains(".")) {
                    str = source.toString();
                } else {
                    str = "";
                }
                if (str.equals(".")) {
                    return "";
                }
            }
            if (!dValue.contains(".")) {
                if (dValue.replace(",", "").length() == 7) {
                    if (!source.equals(".")) {
                        return "";
                    }
                } else {
                    if (dValue.replace(",", "").length() > 7) {
                        return "";
                    }
                }
            } else {
                String split = dValue.substring(0, dValue.replace(",", "").indexOf("."));
                if (dstart <= 7) {
                    if (split.length() == 7) {
                        return "";
                    }
                }
            }
            int index = dValue.indexOf(".");
            if (index < dend) {
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    if (dotValue.length() == 2) {
                        return "";
                    }
                }
            }
            return null;
        }
    };

    public static void setEditNum(EditText et, String string) {
        str = string;
        et.setFilters(new InputFilter[]{lengthFilter});
    }


    /**
     * 禁止首位输入点和零
     */
    public static void setEditNoZeroNumOne(EditText et) {
        et.setFilters(new InputFilter[]{lengthFilterNoZeroOne});
    }


    /**
     * 禁止首位输入点
     */
    public static void setEditNoZeroNumAnd9(EditText et, String string) {
        str = string;
        et.setFilters(new InputFilter[]{lengthFilterNoZero, new InputFilter.LengthFilter(12)});
    }

    /**
     * 禁止首位输入点
     */
    public static void setEditNoZeroNum(EditText et, String string) {
        str = string;
        et.setFilters(new InputFilter[]{lengthFilterNoZero});
    }


    /**
     * 禁止首位输入点
     */
    public static void setEditTwoNum(EditText et, String string) {
        str = string;
        et.setFilters(new InputFilter[]{lengthFilterTwo, new InputFilter.LengthFilter(12)});
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.NUMBER_FORMS
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChineseSymbol(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    static InputFilter textFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!isChinese(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }

    };

    static InputFilter textFilterSymbol = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!isChineseSymbol(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }

    };

    /**
     * 过滤输入框输入emoji和特殊符号
     *
     * @param et
     */
    public static void setTextFilter(EditText et, int lenght) {
        et.setFilters(new InputFilter[]{textFilter, new InputFilter.LengthFilter(lenght)});
    }

    /**
     * 过滤输入框输入emoji和特殊符号只能输入汉字
     *
     * @param et
     */
    public static void setSymbolTextFilter(EditText et, int lenght) {
        et.setFilters(new InputFilter[]{textFilterSymbol, new InputFilter.LengthFilter(lenght)});
    }

    /**
     * 强制隐藏输入法
     *
     * @param context
     * @param view
     */
    public static void hideInput(Context context, EditText view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }


    /**
     * 禁止输入框复制粘贴菜单
     */
    public static void disableCopyAndPaste(final EditText editText) {
        try {
            if (editText == null) {
                return;
            }

            editText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
            editText.setLongClickable(false);
            editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // setInsertionDisabled when user touches the view
                        setInsertionDisabled(editText);
                    }

                    return false;
                }
            });
            editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setInsertionDisabled(EditText editText) {
        try {
            Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(editText);

            // if this view supports insertion handles
            Class editorClass = Class.forName("android.widget.Editor");
            Field mInsertionControllerEnabledField = editorClass.getDeclaredField("mInsertionControllerEnabled");
            mInsertionControllerEnabledField.setAccessible(true);
            mInsertionControllerEnabledField.set(editorObject, false);

            // if this view supports selection handles
            Field mSelectionControllerEnabledField = editorClass.getDeclaredField("mSelectionControllerEnabled");
            mSelectionControllerEnabledField.setAccessible(true);
            mSelectionControllerEnabledField.set(editorObject, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
