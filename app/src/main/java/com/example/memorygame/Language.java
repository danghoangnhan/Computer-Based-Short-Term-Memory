package com.example.memorygame;
import static java.util.Map.entry;

import java.util.Map;

public interface Language {
    enum Key  {
        Objectionable,
        PleaseSelectObjectToColorRing,
        PleaseSelectObjectToRing,
        PleaseSelectThePreviousObject,
        PleaseRememberObject,
        PleaseRememberObjectLocationColor,
        PleaseSelectCorlor,
        Warning,
        ConfirmSkipping,
        Confirm,
        Cancel,
    };
    Map<Key, String> Chinese =  Map.ofEntries(
            entry(Key.Objectionable, "尚未選擇物件"),
            entry(Key.PleaseSelectObjectToColorRing, "放在有顏色的圓圈內"),
            entry(Key.PleaseSelectObjectToRing, "放在圓圈內"),
            entry(Key.PleaseSelectThePreviousObject, "請選出剛才選擇的物件"),
            entry(Key.PleaseRememberObject,"請記得剛所選的物件,位置"),
            entry(Key.PleaseRememberObjectLocationColor,"請記得剛所選的物件,位置,顏色"),
            entry(Key.PleaseSelectCorlor,"請把顏色放到有物件的圓圈"),
            entry(Key.Warning,"提醒"),
            entry(Key.Cancel,"取消"),
            entry(Key.ConfirmSkipping,"確定跳過"),
            entry(Key.Confirm,"確認"));
}