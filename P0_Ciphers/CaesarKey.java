// Tristan Suwito
// 16 April 2025
// CSE 123
// P0: Ciphers
// TA: Rushil Arun & Chris Ma
// This class is a subclass of Substitution and acts as a CaesarKey scheme taking a user-provided
// key creating a custom encoding.
import java.util.*;


public class CaesarKey extends Substitution{
    // Behavior:
    //   - This method constructs a new CaesarKey with the provided key and creates a custom
    //     encoding character and moves the remaining unused characters in the encodable range in 
    //     order to create a custom encoding.
    // Parameters:
    //   - key: String of the initial character sequence for the CaesarKey encoding.
    // Returns:
    // Exceptions:
    //   - if the provided key is null or empty, an IllegalArgumentException is thrown.
    //   - if there is duplicate character in the provided key, an IllegalArgumentException is
    //     thrown
    //   - if any character falls outside the encodable range, an IllegalArgumentException is
    //     thrown.
    public CaesarKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < key.length(); i++) {
            chars.add(key.charAt(i));
        }
        if (chars.size() != key.length()) {
            throw new IllegalArgumentException("Duplicate character in key");
        }
        for (Character c : chars) {
            if (!isCharInRange(c)) {
                throw new IllegalArgumentException("Character out of range");
            }
        }
        String encoding = "";
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            encoding += c;
        }
        for (int i  = 0; i < Cipher.TOTAL_CHARS; i++) {
            char c = ((char)(Cipher.MIN_CHAR + i));
            if(!chars.contains(c)) {
                encoding += c;
            }
        }

        setEncoding(encoding);
    }
}