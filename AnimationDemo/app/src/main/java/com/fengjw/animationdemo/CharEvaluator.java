package com.fengjw.animationdemo;

import android.animation.TypeEvaluator;

/**
 * Created by fengjw on 2017/11/1.
 */

public class CharEvaluator implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float v, Character character, Character t1) {
        int startInt = (int) character;
        int endInt = (int) t1;
        int curInt = (int) (startInt + v * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
