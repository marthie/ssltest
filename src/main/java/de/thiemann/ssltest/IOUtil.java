package de.thiemann.ssltest;

/*
 * ----------------------------------------------------------------------
 * Copyright (c) 2012  Thomas Pornin <pornin@bolet.org>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * ----------------------------------------------------------------------
 * ----------------------------------------------------------------------
 * Copyright (c) 2015  Marius Thiemann <marius.thiemann@ploin.de>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * ----------------------------------------------------------------------
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class IOUtil {

	static final void enc16be(int val, byte[] buf, int off) {
		buf[off] = (byte) (val >>> 8);
		buf[off + 1] = (byte) val;
	}

	static final void enc24be(int val, byte[] buf, int off) {
		buf[off] = (byte) (val >>> 16);
		buf[off + 1] = (byte) (val >>> 8);
		buf[off + 2] = (byte) val;
	}

	static final void enc32be(int val, byte[] buf, int off) {
		buf[off] = (byte) (val >>> 24);
		buf[off + 1] = (byte) (val >>> 16);
		buf[off + 2] = (byte) (val >>> 8);
		buf[off + 3] = (byte) val;
	}

	static final int dec16be(byte[] buf, int off) {
		return ((buf[off] & 0xFF) << 8) | (buf[off + 1] & 0xFF);
	}

	static final int dec24be(byte[] buf, int off) {
		return ((buf[off] & 0xFF) << 16) | ((buf[off + 1] & 0xFF) << 8)
				| (buf[off + 2] & 0xFF);
	}

	static final int dec32be(byte[] buf, int off) {
		return ((buf[off] & 0xFF) << 24) | ((buf[off + 1] & 0xFF) << 16)
				| ((buf[off + 2] & 0xFF) << 8) | (buf[off + 3] & 0xFF);
	}

	static void readFully(InputStream in, byte[] buf) throws IOException {
		readFully(in, buf, 0, buf.length);
	}

	static void readFully(InputStream in, byte[] buf, int off, int len)
			throws IOException {
		while (len > 0) {
			int rlen = in.read(buf, off, len);
			if (rlen < 0) {
				throw new EOFException();
			}
			off += rlen;
			len -= rlen;
		}
	}
}
