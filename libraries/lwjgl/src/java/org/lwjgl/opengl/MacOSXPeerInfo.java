/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

/**
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision: 3116 $
 * $Id: MacOSXPeerInfo.java 3116 2008-08-19 16:46:03Z spasi $
 */
abstract class MacOSXPeerInfo extends PeerInfo {
	MacOSXPeerInfo(PixelFormat pixel_format, boolean use_display_bpp, boolean support_window, boolean support_pbuffer, boolean double_buffered) throws LWJGLException {
		super(createHandle());
		if (pixel_format.isFloatingPoint() && !LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4))
			throw new LWJGLException("Floating point pixel format requested, but is not supported");
		choosePixelFormat(pixel_format, use_display_bpp, support_window, support_pbuffer, double_buffered);
	}
	private static native ByteBuffer createHandle();

	private void choosePixelFormat(PixelFormat pixel_format, boolean use_display_bpp, boolean support_window, boolean support_pbuffer, boolean double_buffered) throws LWJGLException {
		nChoosePixelFormat(getHandle(), pixel_format, use_display_bpp, support_window, support_pbuffer, double_buffered);
	}
	private static native void nChoosePixelFormat(ByteBuffer peer_info_handle, PixelFormat pixel_format, boolean use_display_bpp, boolean support_window, boolean support_pbuffer, boolean double_buffered) throws LWJGLException;

	public void destroy() {
		nDestroy(getHandle());
	}
	private static native void nDestroy(ByteBuffer handle);
}
