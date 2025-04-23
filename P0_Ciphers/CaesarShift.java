// Tristan Suwito
// 16 April 2025
// CSE 123
// P0: Ciphers
// TA: Rushil Arun & Chris Ma
// This class is a subclass of Substitution and acts as a CasesarShift scheme that shifts all
// encodable characters to the left by some provided shift amount.
public class CaesarShift extends Substitution{

    // Behavior:
    //   - This method constructs a new CaesarShift with the provided shift value and shifts each
    //     character in the encodable range by a specified number of positions.
    // Parameters:
    //   - shift: int of the provided shift value.
    // Returns:
    // Exceptions:
    //   - if the provided shift value is not greater than 0, an IllegalArgumentException is
    //     thrown.
    public CaesarShift(int shift) {
        if(shift <= 0) {
            throw new IllegalArgumentException("Shift must be greater than 0");
        }
        shift %= Cipher.TOTAL_CHARS;
        String createdEncoding = "";
        for (int i = 0; i < Cipher.TOTAL_CHARS; i++) {
            createdEncoding += ((char)(Cipher.MIN_CHAR + (i + shift) % Cipher.TOTAL_CHARS));

        }
        setEncoding(createdEncoding);
    }
}