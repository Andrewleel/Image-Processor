import java.io.IOException;

/**
 * class for testing purposes to throw IOException for our playGame method in controller class.
 */
public class CorruptAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("IOException from Appendable");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("IOException from Appendable");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("IOException from Appendable");
  }
}
