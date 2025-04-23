// Tristan Suwito
// 16 April 2025
// CSE 123
// P0: Ciphers
// TA: Rushil Arun & Chris Ma
// This class is a subclass of Cipher and chains ciphers together for encrption and decryption.
import java.util.*;
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;

    // Behavior:
    //   - This method constructs a new MultiCipher with the provided List of Ciphers.
    // Parameters:
    //   - ciphers: a list of provided ciphers.
    // Returns:
    // Exceptions:
    //   - if 'ciphers' is null, an IllegalArgumentException is thrown. 
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException("Ciphers cannot be null");
        }

        this.ciphers = ciphers;
    }

    // Behavior:
    //   - This method applies the MultiCipher encryption scheme to 'input', returning the result.
    // Parameters:
    //   - input: the string to be encrypted. Should be non-null and all characters of should be 
    //            within the encodable range.
    // Returns:
    //   - Returns the result of applying the MultiCipher encryption scheme to 'input'. 
    // Exceptions:
    //   - if 'input' is null, an IllegalArgumentException is thrown.
    @Override
    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        String encryptedInput = input;
        
        for (Cipher cipher : ciphers) {
            encryptedInput = cipher.encrypt(encryptedInput);
        }
        return encryptedInput;
    }

    // Behavior:
    //   - This method applies this inverse of the MultiCipher encryption scheme to 'input' 
    //    (reversing a single round of encryption if previously applied), returning the result.
    // Parameters:
    //   - input: the string to be decrypted. Should be non-null and all characters of should be 
    //            within the encodable range.
    // Returns:
    //   - Returns the result of applying the inverse of the MultiCipher encryption scheme to 
    //    `input`
    // Exceptions:
    //   - if 'input' is null, an IllegalArgumentException is thrown.
    @Override
    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        String decryptedInput = input;

        for(int i = ciphers.size() - 1; i >= 0; i--) {
            Cipher curr = ciphers.get(i);
            decryptedInput = curr.decrypt(decryptedInput);
        }
        return decryptedInput;
    }
}