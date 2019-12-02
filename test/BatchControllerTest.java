import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import controller.BatchController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents all the methods that tests the controller class of this project.
 */
public class BatchControllerTest {
  private BatchController control;

  /**
   * Initialises a new instance of controller for testing.
   *
   * @throws Exception thrown if object isn't created.
   */
  @Before
  public void setUp() throws Exception {
    control = new BatchController();
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if user tries to apply
   * filter without loading the image.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void directCommand() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("dither");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("No image to work on!", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user tries to
   * execute without a script file.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void noFile() throws IOException {
    FileReader f = null;
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad input: null", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user tries to
   * load an image without specifying the path.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void NoLoadArg() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("No load path/file!", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user tries to
   * save generated image without giving the path.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void NoSaveArg() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("save\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("No output to write!", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user directly
   * tries to save without loading and generating the image.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void saveNull() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("save res/sample.png\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("No output to write!", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user tries to
   * save a file in an invalid format.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void badSaveFormat() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load res/mojo.jpg\ndither\nsave res/sample.pnr\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad saving format: pnr", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user tries to
   * execute a command that does not exist/ is invalid.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void nonExistingCommand() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load res/mojo.jpg\nrack\nsave res/sample.pnr\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad command: rack", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user passes an
   * invalid parameter instead of colon when specifying the image arguments such as dimensions.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void badParameterFormat() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load res/mojo.jpg\ngreece width-1000\nsave res/sample.pnr\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad parameter!", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user passes
   * float point numbers as the image arguments instead of integer.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void floatParameter() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load res/mojo.jpg\ngreece width:1000.56\nsave res/sample.pnr\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad width parameter!", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user tries to
   * pass an invalid parameter for an operation.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void badParameter() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load res/mojo.jpg\ngreece shoesize:1000\nsave res/sample.pnr\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Unknown parameter passed: shoesize:1000", e.getMessage());
    }
  }

  /**
   * This method tests if an IllegalArgument Exception is thrown as expected if the user passes a
   * invalid seed value for mosaic filtering.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void badMosaicSeed() throws IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("test/script.txt"), StandardCharsets.UTF_8));
    writer.write("load res/mojo.jpg\nmosaic seeds:-10\nsave res/sample.pnr\nsepia");
    writer.close();
    FileReader f = new FileReader("test/script.txt");
    try {
      control.execute(f);
      fail("Exception must have been thrown!");
    } catch (IllegalArgumentException e) {
      assertEquals("Bad seed value: -10", e.getMessage());
    }
  }

  /**
   * This method tests if the program behaves as expected when the commands are passed as a string.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void stringInput() throws IOException {
    StringReader st1 = new StringReader("load res/mojo.jpg\ndither\nsave test/dither-mojo.jpg");
    try {
      control.execute(st1);
    } catch (Throwable t) {
      fail("No exception must be thrown here!");
    }
  }

  /**
   * This method tests if the program behaves as expected when a script with valid arguments and
   * operations are passed.
   *
   * @throws IOException when there is a problem reading or writing the file
   */
  @Test
  public void aBigValidTest() throws IOException {
    FileReader f = new FileReader("res/script.txt");
    try {
      control.execute(f);
    } catch (Throwable t) {
      fail("No exception must be thrown here!");
    }
  }
}