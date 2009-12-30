package prfl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.impl.DefaultFileSystemManager;
import org.apache.commons.vfs.provider.sftp.SftpFileSystemConfigBuilder;

public class FTPConnector {
	static String user = "TRNPAY";
	static String password  =  "TRN3641!";
	static String host = "64.132.121.142:9005";
	
	public static void copyFileToRemote() throws IOException{
		
		String file = "src/test/files/TRNPAY.20091221150100.ach.csv";
		String remote = "TRNPAY.20091221150100.ach.csv";
		//remote = "dfd";
		FileObject fo = getRemoteFileObject("");
		
		//fo.co
		//fo.createFolder();
		fo.createFile();
		
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
	    // open output stream to local file
	    OutputStream os = new BufferedOutputStream(fo.getContent().getOutputStream());
	    int c;
	    // do copying
	    while ((c = is.read()) != -1) {
	        os.write(c);
	    }
	    os.close();
	    is.close();
	    // close the file object
	    fo.close();
		
	}
	
	public static FileObject  getRemoteFileObject(String name) throws FileSystemException{
		FileSystemOptions fsOptions = new FileSystemOptions();
	    SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
	            fsOptions, "no");
	    SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(fsOptions, true);
	    
	    // now we create a new filesystem manager
	    DefaultFileSystemManager fsManager = (DefaultFileSystemManager) VFS
	            .getManager();
	    // the url is of form sftp://user:pass@host/remotepath/
	    String uri = "sftp://" + user + ":" + password + "@" + host
	            + "/virtual_directory/" + name;
	    // get file object representing the local file
	    FileObject fo = fsManager.resolveFile(uri, fsOptions);
	    return fo;
	}
	
	public static void copyRemoteFile(String remotePath, String localPath) throws IOException {
		    // we first set strict key checking off
		    FileObject fo = getRemoteFileObject(remotePath);
		    // open input stream from the remote file
		    BufferedInputStream is = new BufferedInputStream(fo.getContent()
		            .getInputStream());
		    // open output stream to local file
		    OutputStream os = new BufferedOutputStream(new FileOutputStream(
		            localPath));
		    int c;
		    // do copying
		    while ((c = is.read()) != -1) {
		        os.write(c);
		    }
		    os.close();
		    is.close();
		    // close the file object
		    fo.close();
		    // NOTE: if you close the file system manager, you won't be able to 
		    // use VFS again in the same VM. If you wish to copy multiple files,
		    // make the fsManager static, initialize it once, and close just
		    // before exiting the process.
		   // fsManager.close();
		    System.out.println("Finished copying the file");
		}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FTPClient f= new FTPClient();
		//SftpClientFactory clientFactory = SftpClientFactory.
		String url = "74.52.67.146";
		String password = "witchcraft";
		String userName = "joaquin@b2bbargains.com";
	    
	    try {
	    	//copyRemoteFile("NOC.200912221910011863.csv", "mycopy.csv");
	    	copyFileToRemote();
			/*
	    	f.setDefaultPort(9005);
	       
	    	f.connect("64.132.121.142");
	    	
			//f.login("TRNPAY", "SPH7846$");
	       // f.connect(url);
	    	//f.login(userName, password);
			FTPFile[] files = f.listFiles();
			
			for (FTPFile file : files) {
				if(file.getName().endsWith("txt"))
					f.retrieveFile(file.getName(), System.out);
				System.out.println(file.getName());
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     //listFiles("/");

	}

}
