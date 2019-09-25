package com.rscoelho.java.functional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Writer.class })
public class WriterTest_JunitFive {

	@Test
	public void use_withMock_shouldThrowsException() throws Exception {
		final IMethodPattern<Writer, IOException> cons = writer -> writer.writeStuff("ok it's really working ...");

		final FileWriter myFile = Mockito.mock(FileWriter.class);
		doThrow(IOException.class).when(myFile).write(anyString());

		PowerMockito.whenNew(FileWriter.class).withAnyArguments().thenReturn(myFile);

		assertThrows(IOException.class, () -> Writer.use("exception.txt", cons));
	}
}
