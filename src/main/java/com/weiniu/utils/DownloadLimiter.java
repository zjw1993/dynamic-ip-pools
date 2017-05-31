package com.weiniu.utils;

import java.io.IOException;
import java.io.InputStream;

public class DownloadLimiter extends InputStream {
	 private InputStream is = null;
	 private BandwidthLimiter bandwidthLimiter = null;

	 public DownloadLimiter(InputStream is, BandwidthLimiter bandwidthLimiter){
		 this.is = is;
		 this.bandwidthLimiter = bandwidthLimiter;
	 }
	 
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		if(this.bandwidthLimiter != null)
			this.bandwidthLimiter.limitNextBytes();
		return  this.is.read();
	}
	
	public int read(byte b[], int off, int len) throws IOException{
		if (bandwidthLimiter != null)
			bandwidthLimiter.limitNextBytes(len);
		return this.is.read(b, off, len);
	}

}
