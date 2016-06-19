package com.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2016-05-07.
 */
public class FileUtil {

	public static void main(String[] args) throws IOException {
		new FileUtil().readTextByLines(new File("demo/raf.dat"));
	}
	

	/**
	 * �����ݱ������ļ�
	 * @param sourceFile
	 * @param content
	 * @throws IOException
	 */
	public void save(File sourceFile, String content) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(sourceFile, true);
		outputStream.write(content.getBytes());
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void getFileHexTextByByte(String fileName) throws IOException{
		InputStream inputStream = new FileInputStream(fileName);
		int b = 0;
		while((b = inputStream.read()) != -1){
			System.out.println(Integer.toHexString(b));
		}
		inputStream.close();
	}
	
	/**
	 * �ʺ϶�ȡ�ϴ��ļ�
	 * @param fileName
	 * @throws IOException
	 */
	public void getFileHexTextByArray(String fileName) throws IOException{
		InputStream inputStream = new FileInputStream(fileName);
		//����̫��Ҳ����̫С
		byte[] buffer = new byte[5 * 1024];
		int len = 0;
		while(( len = inputStream.read(buffer, 0, buffer.length)) != -1){
			for(int i = 0; i < buffer.length; i ++){
				System.out.println(Integer.toHexString(i));
			}
		}
		inputStream.close();
	}
	
	/**
	 * һ��һ�ж�ȡ�ı������
	 * @param sourcefile
	 * @throws IOException
	 */
	public void readTextByLines(File sourceFile) throws IOException{
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException("Ŀ¼" + sourceFile + "������");
		}

		if (!sourceFile.isFile()) {
			throw new IllegalArgumentException("�����ļ�");
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(sourceFile)));
		String str;
		while((str = br.readLine()) != null){
			System.out.println(str);
		}
		br.close();
	}
	
	/**
	 * ���ֽڷ�ʽ��ȡ�ļ����ݲ�����
	 * @param strFilePath
	 * @return ��ȡ���ļ�����
	 * @throws IOException
	 */
	public String readTxtFile(File file) throws IOException {
		if (!file.exists()) {
			throw new IllegalArgumentException("�ļ�" + file + "������");
		}

		if (!file.isFile()) {
			throw new IllegalArgumentException("�����ļ�");
		}
 
		String content = ""; 
		BufferedReader buffreader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(file)));
		StringBuilder sb = new StringBuilder();
		String s;
		// ���ж�ȡ
		while ((s = buffreader.readLine()) != null) {
			sb.append(s);
		}
		buffreader.close();
	 
		return content;
	}

	
	/**
	 * ͨ��һ�����ַ�����ȡ�ļ������ݲ����
	 * @param sourceFile
	 * @throws IOException
	 */
	public void readTextByChar(File sourceFile) throws IOException{
		InputStreamReader isr = new InputStreamReader(new FileInputStream(sourceFile));
		int c = 0;
		while(( c = isr.read() ) != -1){
			System.out.println((char)c);
		}
		isr.close();
	}
	
	/**
	 * ͨ�����ַ������ַ�����������
	 * @param sourceFile
	 * @throws IOException
	 */
	public void readTextByCharArray(File sourceFile) throws IOException{
		InputStreamReader isr = new InputStreamReader(new FileInputStream(sourceFile));
		char[] buffer = new char[4 * 1024];
		int c = 0;
		StringBuilder sb = new StringBuilder();
		while(( c = isr.read(buffer,0,buffer.length) ) != -1){
			sb.append(buffer);
		}
		System.out.println(sb.toString());
		isr.close();
	}
	
	/**
	 * ͨ���ֽڷ�ʽ�����ļ�
	 * @param sourceFile
	 * @param destFile
	 * @throws IOException
	 */
	public void copyFileByByte(File sourceFile,File destFile) throws IOException{
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException("Ŀ¼" + sourceFile + "������");
		}

		if (!sourceFile.isFile()) {
			throw new IllegalArgumentException("�����ļ�");
		}
		
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(destFile,true) ;
		int len = 0;
		while((len = inputStream.read()) != -1){
			outputStream.write(len);//д��ʵ����Ч�ֽ���
			outputStream.flush();
		}
		inputStream.close();
		outputStream.close();
	}
	
	/**
	 * ͨ���ֽ����鸴���ļ�
	 * @param sourceFile
	 * @param destFile
	 * @throws IOException
	 */
	public void copyFileByByteArray(File sourceFile,File destFile) throws IOException{
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException("Ŀ¼" + sourceFile + "������");
		}

		if (!sourceFile.isFile()) {
			throw new IllegalArgumentException("�����ļ�");
		}
		
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(destFile,true) ;
		byte[] buff = new byte[4 * 1024];
		int len = 0;
		while((len = inputStream.read(buff,0,buff.length)) != -1){
			outputStream.write(buff,0,len);//д��ʵ����Ч�ֽ���
			outputStream.flush();
		}
		inputStream.close();
		outputStream.close();
	}
	
	/**
	 * һ���ֽ�һ���ֽڵĸ��ƣ������л�����
	 * @param sourceFile
	 * @param destFile
	 * @throws IOException
	 */
	public void copyFileByBuffer(File sourceFile,File destFile) throws IOException{
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException("Ŀ¼" + sourceFile + "������");
		}

		if (!sourceFile.isFile()) {
			throw new IllegalArgumentException("�����ļ�");
		}
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
		int len = 0;
		while((len = bis.read()) != -1){
			bos.write(len);
			bos.flush();
		}
		bis.close();
		bos.close();
	}

	/**
	 * �ݹ��г�Ŀ¼�µ��ļ����ļ���
	 * @param dir
	 */
	public void listDirectory(File dir) {
		if (!dir.exists()) {
			throw new IllegalArgumentException("Ŀ¼" + dir + "������");
		}

		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("����Ŀ¼");
		}

		/*
		 * �г���ǰĿ¼�µ����� String[] list = dir.list(); for(String l : list){
		 * System.out.println(dir+"\\"+l); }
		 */

		File[] files = dir.listFiles();
		if (files != null && files.length != 0) {
			for (File f : files) {
				if (f.isDirectory()) {
					listDirectory(f);
				} else {
					System.out.println(f);
				}
			}
		}
	}

	public void removeFile(File file) {
		file.delete();
	}

	public boolean changeFileName(File sourceFile, File destFile) {
		return sourceFile.renameTo(destFile) && sourceFile.delete();
	}

	public boolean isFileExist(String fileFullName) {
		File file = new File(fileFullName);
		if (!file.exists())
			return false;
		return true;
	}

 	public boolean copyFile(File sourceFile, File destFile) {
		try {
			String content = readTxtFile(sourceFile);
			save(destFile, content);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<String> showAllCard(String fullFileName) {
		File file = new File(fullFileName);
		List<String> listData = new ArrayList<String>();

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			InputStream instream = new FileInputStream(file);
			if (instream != null) {
				InputStreamReader inputReader = new InputStreamReader(instream);
				BufferedReader bufferReader = new BufferedReader(inputReader);
				String line;
				// ���ж�ȡ
				while ((line = bufferReader.readLine()) != null) {
					listData.add(line.split("\t")[1]);
				}
				inputReader.close();
				instream.close();
				bufferReader.close();
			}
			return listData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {

		}
	}

	public boolean addToConfigureFile(String cardId, String cardName, String configureFileName) {

		File file = new File(configureFileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			InputStream instream = new FileInputStream(file);
			if (instream != null) {
				InputStreamReader inputReader = new InputStreamReader(instream);
				BufferedReader bufferReader = new BufferedReader(inputReader);
				String line;
				// ���ж�ȡ
				while ((line = bufferReader.readLine()) != null) {
					String[] cardInfo = line.split("\t");
					if (cardInfo[0].equals(cardId) || cardInfo[1].equals(cardName)) {
						return false;
					}
				}
				inputReader.close();
				instream.close();
				bufferReader.close();
			}

			FileWriter writer = new FileWriter(configureFileName, true);
			writer.write(cardId + "\t" + cardName + "\n");
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
