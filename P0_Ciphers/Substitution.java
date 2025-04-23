// Tristan Suwito
// 16 April 2025
// CSE 123
// P0: Ciphers
// TA: Rushil Arun & Chris Ma
// This class is a subclass of Cipher and acts as a substitution cipher that assigns each character
// to a different unique character based on a provided encoding string.
import java.util.*;

public class Substitution extends Cipher {
    private String encoding;

    // Behavior:
    //   - This method constructs a new Substitution Cipher with an empty encoding.
    // Parameters:
    // Returns:
    // Exceptions:
    public Substitution() {
        this.encoding = null;
    }

    // Behavior:
    //   - This method constructs a new Substitution Cipher with the provided encoding.
    // Parameters:
    //   - encoding: String of output characters corresponding to the input characters.
    // Returns:
    // Exceptions:
    //   - if encoding is null, IllegalArgumentException is thrown.
    //   - if length of the encoding doesn't match the number of characters within our Cipher's
    //     encodable range, IllegalArgumentException is thrown.
    //   - if encoding contains a duplicate character, an IllegalArgumentException is thrown.
    //   - if any encoding character falls outside the encodable range, an IllegalArgumentException
    //     is thrown.
    public Substitution(String encoding) {
        setEncoding(encoding);

    }

    // Behavior:
    //   - This method updates the encoding for this Substitution Cipher.
    // Parameters:
    //   - encoding: String of output characters corresponding to the input characters.
    // Returns:
    // Exceptions:
    //   - if encoding is null, IllegalArgumentException is thrown.
    //   - if length of the encoding doesn't match the number of characters within our Cipher's
    //     encodable range, IllegalArgumentException is thrown.
    //   - if encoding contains a duplicate character, an IllegalArgumentException is thrown.
    //   - if any encoding character falls outside the encodable range, an IllegalArgumentException
    //     is thrown.
    public void setEncoding(String encoding) {
        if (encoding == null) {
            throw new IllegalArgumentException("Encoding cannot be null");
        } 
        if (encoding.length() != Cipher.TOTAL_CHARS) {
            throw new IllegalArgumentException("Encoding length must be" + Cipher.TOTAL_CHARS);
        }
        Set<Character> dupeCheck = new HashSet<>();
        for (int i = 0; i < encoding.length(); i++) {
            dupeCheck.add(encoding.charAt(i));
        }
        if (encoding.length() != dupeCheck.size()) {
            throw new IllegalArgumentException("Duplicate character in encoding");
        }
        for(char c : dupeCheck) {
            if (!isCharInRange(c)) {
                throw new IllegalArgumentException("Character out of range");
            }
        }
        this.encoding = encoding;
    }

    // Behavior:
    //   - This method applies the substitution encryption scheme to 'input', returning the result.
    // Parameters:
    //   - input: the string to be encrypted. Should be non-null and all characters of
    //            should be within the encodable range.
    // Returns:
    //   - Returns the result of applying the substitution encryption scheme to 'input'. 
    // Exceptions:
    //   - if input is null, IllegalArgumentException is thrown.
    //   - if encoding is not set, IllegalStateException is thrown.
    @Override
    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (encoding == null) {
            throw new IllegalStateException("Encoding not set");
        }
        String encryptedInput = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int index = c - Cipher.MIN_CHAR;
            encryptedInput = encryptedInput + encoding.charAt(index);
        }
        return encryptedInput;
    }

    // Behavior:
    //   - This method applies this inverse of the substitution encryption scheme to 'input' 
    //    (reversing a single round of encryption if previously applied), returning the result.
    // Parameters:
    //   - input: the string to be decrypted. Should be non-null and all characters of
    //            should be within the encodable range.
    // Returns:
    //   - Returns the result of applying the inverse of the substitution encryption scheme to 
    //    `input`. 
    // Exceptions:
    //   - if input is null, IllegalArgumentException is thrown.
    //   - if encoding is not set, IllegalStateException is thrown.
    @Override
    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (encoding == null) {
            throw new IllegalStateException("Encoding not set");
        }
        String decryptedInput = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int index = encoding.indexOf(c);
            decryptedInput = decryptedInput + ((char)(Cipher.MIN_CHAR + index));
        }
        return decryptedInput;
    }
}