package by.ilyineugene.arrayapp.validator;

public class ArrayStringValidatorImpl {

    public static boolean isValid(String string) {
        boolean isValid = false;
        if (string != null && !string.isEmpty()) {
            char[] allowedSeparators = new char[]{'.', ' ', '-', ','}; //in properties file ?
            int stringLength = string.length();
            int stringIndex = 0;
            while (stringIndex < stringLength) {
                char checkedCharacter = string.charAt(stringIndex);
                boolean isSeparator = false;
                for (int separatorIndex = 0; separatorIndex < allowedSeparators.length; separatorIndex++) {
                    if (checkedCharacter == allowedSeparators[separatorIndex]) {
                        isSeparator = true;
                    }
                }
                isValid = isSeparator || (checkedCharacter >= 48 && checkedCharacter <= 57);
                if (!isValid) {
                    break; //the string is invalid at the current character.
                }
                stringIndex++;
            }
        }
        return isValid;
    }

}
