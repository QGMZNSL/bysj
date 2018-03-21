package com.sincinfo.zxks.core.plan.examarrange;

import jxl.SheetSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;

public class ExcelUtil {
	private WritableFont normalFont = new WritableFont(WritableFont.ARIAL,9);
	private WritableFont normalRedFont = new WritableFont(WritableFont.ARIAL,9,WritableFont.NO_BOLD,
			false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
	private WritableFont BoldFont = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
	private WritableFont titleFont = new WritableFont(WritableFont.ARIAL,24,WritableFont.BOLD);
	private WritableFont normalFontH2 = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD);
	
	public WritableCellFormat getHeaderleftAlignLeft(){
		WritableCellFormat headerleft = new WritableCellFormat(normalFont);
		try{
			headerleft.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			headerleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerleft.setBackground(Colour.PALE_BLUE);
			headerleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerleft.setAlignment(Alignment.LEFT);
			headerleft.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderleftAlignLeft:"+e.toString());
		 }
		return headerleft;
	}
	
	public WritableCellFormat getHeaderleftAlignRight(){
		WritableCellFormat headerleft = new WritableCellFormat(normalFont);
		try{
			headerleft.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			headerleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerleft.setBackground(Colour.PALE_BLUE);
			headerleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerleft.setAlignment(Alignment.RIGHT);
			headerleft.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderleftAlignRight:"+e.toString());
		 }
		return headerleft;
	}
	
	public WritableCellFormat getHeaderleftAlignCenter(){
		WritableCellFormat headerleft = new WritableCellFormat(normalFont);
		try{
			headerleft.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			headerleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerleft.setBackground(Colour.PALE_BLUE);
			headerleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerleft.setAlignment(Alignment.CENTRE);
			headerleft.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderleftAlignCenter:"+e.toString());
		 }
		return headerleft;
	}
	
	public WritableCellFormat getHeaderLeftRightAlignCenter(){
		WritableCellFormat headerleft = new WritableCellFormat(normalFont);
		try{
			headerleft.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			headerleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerleft.setBackground(Colour.PALE_BLUE);
			headerleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerleft.setAlignment(Alignment.CENTRE);
			headerleft.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderleftAlignCenter:"+e.toString());
		 }
		return headerleft;
	}
	
	public WritableCellFormat getHeaderrightAlignLeft(){
		WritableCellFormat headerright = new WritableCellFormat(normalFont);
		try{
			headerright.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			headerright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			headerright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerright.setBackground(Colour.PALE_BLUE);
			headerright.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerright.setAlignment(Alignment.LEFT);
			headerright.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderrightAlignLeft:"+e.toString());
		 }
		return headerright;
	}
	
	
	public WritableCellFormat getHeaderrightAlignRight(){
		WritableCellFormat headerright = new WritableCellFormat(normalFont);
		try{
			headerright.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			headerright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			headerright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerright.setBackground(Colour.PALE_BLUE);
			headerright.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerright.setAlignment(Alignment.RIGHT);
			headerright.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderrightAlignRight:"+e.toString());
		 }
		return headerright;
	}
	
	
	public WritableCellFormat getHeaderrightAlignCenter(){
		WritableCellFormat headerright = new WritableCellFormat(normalFont);
		try{
			headerright.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			headerright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			headerright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerright.setBackground(Colour.PALE_BLUE);
			headerright.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerright.setAlignment(Alignment.CENTRE);
			headerright.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderrightAlignCenter:"+e.toString());
		 }
		return headerright;
	}
	
	public WritableCellFormat getHeaderformatAlignLeft(){
		WritableCellFormat headerformat = new WritableCellFormat(normalFont);
		try{
			headerformat.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			headerformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			headerformat.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerformat.setBackground(Colour.PALE_BLUE);
			headerformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerformat.setAlignment(Alignment.LEFT);
			headerformat.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderformatAlignLeft:"+e.toString());
		 }
		return headerformat;
	}
	
	public WritableCellFormat getHeaderformatAlignRight(){
		WritableCellFormat headerformat = new WritableCellFormat(normalFont);
		try{
			headerformat.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			headerformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			headerformat.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerformat.setBackground(Colour.PALE_BLUE);
			headerformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerformat.setAlignment(Alignment.RIGHT);
			headerformat.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderformatAlignRight:"+e.toString());
		 }
		return headerformat;
	}
	
	public WritableCellFormat getHeaderformatAlignCenter(){
		WritableCellFormat headerformat = new WritableCellFormat(normalFont);
		try{
			headerformat.setBorder(Border.TOP, BorderLineStyle.DOUBLE);
			headerformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			headerformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			headerformat.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			headerformat.setBackground(Colour.PALE_BLUE);
			headerformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			headerformat.setAlignment(Alignment.CENTRE);
			headerformat.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getHeaderformatAlignCenter:"+e.toString());
		 }
		return headerformat;
	}
	
	public WritableCellFormat getBodyleftAlignLeft(){
		WritableCellFormat bodyleft = new WritableCellFormat(normalFont);
		try{
			bodyleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bodyleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyleft.setAlignment(Alignment.LEFT);
			bodyleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyleftAlignLeft:"+e.toString());
		 }
		return bodyleft;		
	}
	
	public WritableCellFormat getBodyleftAlignRight(){
		WritableCellFormat bodyleft = new WritableCellFormat(normalFont);
		try{
			bodyleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bodyleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyleft.setAlignment(Alignment.RIGHT);
			bodyleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyleftAlignRight:"+e.toString());
		 }
		return bodyleft;		
	}
	
	public WritableCellFormat getBodyleftAlignCenter(){
		WritableCellFormat bodyleft = new WritableCellFormat(normalFont);
		try{
			bodyleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bodyleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyleft.setAlignment(Alignment.CENTRE);
			bodyleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyleftAlignCenter:"+e.toString());
		 }
		return bodyleft;		
	}
	
	public WritableCellFormat getBodyrightAlignLeft(){
		WritableCellFormat bodyright = new WritableCellFormat(normalFont);
		try{
			bodyright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bodyright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyright.setAlignment(Alignment.LEFT);
			bodyright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyrightAlignLeft:"+e.toString());
		 }
		return bodyright;		
	}
	
	public WritableCellFormat getBodyrightAlignRight(){
		WritableCellFormat bodyright = new WritableCellFormat(normalFont);
		try{
			bodyright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bodyright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyright.setAlignment(Alignment.RIGHT);
			bodyright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyrightAlignRight:"+e.toString());
		 }
		return bodyright;		
	}
	
	public WritableCellFormat getBodyrightAlignCenter(){
		WritableCellFormat bodyright = new WritableCellFormat(normalFont);
		try{
			bodyright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bodyright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyright.setAlignment(Alignment.CENTRE);
			bodyright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyrightAlignCenter:"+e.toString());
		 }
		return bodyright;		
	}
	
	public WritableCellFormat getBodyformatAlignLeft(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.THIN);
			bodyformat.setVerticalAlignment(VerticalAlignment.TOP);
			bodyformat.setAlignment(Alignment.LEFT);
			bodyformat.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyformatAlignLeft:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getBodyformatAlignLeftCenter(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.THIN);
			bodyformat.setVerticalAlignment(VerticalAlignment.CENTRE);
			bodyformat.setAlignment(Alignment.LEFT);
			bodyformat.setWrap(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyformatAlignLeft:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getBodyformatAlignRight(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.HAIR);
			bodyformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyformat.setAlignment(Alignment.RIGHT);
			bodyformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyformatAlignRight:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getBodyformatAlignCenter(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.THIN);
			bodyformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyformat.setAlignment(Alignment.CENTRE);
			bodyformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyformatAlignCenter:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getBodyRedleftAlignLeft(){
		WritableCellFormat bodyleft = new WritableCellFormat(normalRedFont);
		try{
			bodyleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bodyleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyleft.setAlignment(Alignment.LEFT);
			bodyleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedleftAlignLeft:"+e.toString());
		 }
		return bodyleft;		
	}
	
	public WritableCellFormat getBodyRedleftAlignRight(){
		WritableCellFormat bodyleft = new WritableCellFormat(normalRedFont);
		try{
			bodyleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bodyleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyleft.setAlignment(Alignment.RIGHT);
			bodyleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedleftAlignRight:"+e.toString());
		 }
		return bodyleft;		
	}
	
	public WritableCellFormat getBodyRedleftAlignCenter(){
		WritableCellFormat bodyleft = new WritableCellFormat(normalRedFont);
		try{
			bodyleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bodyleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bodyleft.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyleft.setAlignment(Alignment.CENTRE);
			bodyleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedleftAlignCenter:"+e.toString());
		 }
		return bodyleft;		
	}
	
	public WritableCellFormat getBodyRedrightAlignLeft(){
		WritableCellFormat bodyright = new WritableCellFormat(normalRedFont);
		try{
			bodyright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bodyright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyright.setAlignment(Alignment.LEFT);
			bodyright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedrightAlignLeft:"+e.toString());
		 }
		return bodyright;		
	}
	
	public WritableCellFormat getBodyRedrightAlignRight(){
		WritableCellFormat bodyright = new WritableCellFormat(normalRedFont);
		try{
			bodyright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bodyright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyright.setAlignment(Alignment.RIGHT);
			bodyright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedrightAlignRight:"+e.toString());
		 }
		return bodyright;		
	}
	
	public WritableCellFormat getBodyRedrightAlignCenter(){
		WritableCellFormat bodyright = new WritableCellFormat(normalRedFont);
		try{
			bodyright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bodyright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bodyright.setBorder(Border.BOTTOM, BorderLineStyle.HAIR);
			bodyright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyright.setAlignment(Alignment.CENTRE);
			bodyright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedrightAlignCenter:"+e.toString());
		 }
		return bodyright;		
	}
	
	public WritableCellFormat getBodyRedformatAlignLeft(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalRedFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.HAIR);
			bodyformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyformat.setAlignment(Alignment.LEFT);
			bodyformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedformatAlignLeft:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getBodyRedformatAlignRight(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalRedFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.HAIR);
			bodyformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyformat.setAlignment(Alignment.RIGHT);
			bodyformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedformatAlignRight:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getBodyRedformatAlignCenter(){
		WritableCellFormat bodyformat = new WritableCellFormat(normalRedFont);
		try{
			bodyformat.setBorder(Border.ALL, BorderLineStyle.HAIR);
			bodyformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bodyformat.setAlignment(Alignment.CENTRE);
			bodyformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyRedformatAlignCenter:"+e.toString());
		 }
		return bodyformat;		
	}
	
	public WritableCellFormat getFooterBoldleft(){
		WritableCellFormat footerleft = new WritableCellFormat(BoldFont);
		try{
			footerleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			footerleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			footerleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			footerleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			footerleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			footerleft.setAlignment(Alignment.LEFT);
			footerleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getFooterBoldleft:"+e.toString());
		 }
		return footerleft;		
	}
	
	public WritableCellFormat getBottomleftAlignLeft(){
		WritableCellFormat bottomleft = new WritableCellFormat(normalFont);
		try{
			bottomleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bottomleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomleft.setAlignment(Alignment.LEFT);
			bottomleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomleftAlignLeft:"+e.toString());
		 }
		return bottomleft;		
	}
	
	public WritableCellFormat getBottomleftAlignRight(){
		WritableCellFormat bottomleft = new WritableCellFormat(normalFont);
		try{
			bottomleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bottomleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomleft.setAlignment(Alignment.RIGHT);
			bottomleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomleftAlignRight:"+e.toString());
		 }
		return bottomleft;		
	}
	
	public WritableCellFormat getBottomleftAlignCenter(){
		WritableCellFormat bottomleft = new WritableCellFormat(normalFont);
		try{
			bottomleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bottomleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomleft.setAlignment(Alignment.CENTRE);
			bottomleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomleftAlignCenter:"+e.toString());
		 }
		return bottomleft;		
	}
	
	public WritableCellFormat getBottomrightAlignLeft(){
		WritableCellFormat bottomright = new WritableCellFormat(normalFont);
		try{
			bottomright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bottomright.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomright.setAlignment(Alignment.LEFT);
			bottomright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomrightAlignLeft:"+e.toString());
		 }
		return bottomright;		
	}
	
	public WritableCellFormat getBottomrightAlignRight(){
		WritableCellFormat bottomright = new WritableCellFormat(normalFont);
		try{
			bottomright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bottomright.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomright.setAlignment(Alignment.RIGHT);
			bottomright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomrightAlignRight:"+e.toString());
		 }
		return bottomright;		
	}
	
	public WritableCellFormat getBottomrightAlignCenter(){
		WritableCellFormat bottomright = new WritableCellFormat(normalFont);
		try{
			bottomright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bottomright.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomright.setAlignment(Alignment.CENTRE);
			bottomright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomrightAlignCenter:"+e.toString());
		 }
		return bottomright;		
	}
	
	public WritableCellFormat getBottomformatAlignLeft(){
		WritableCellFormat bottomformat = new WritableCellFormat(normalFont);
		try{
			bottomformat.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomformat.setAlignment(Alignment.LEFT);
			bottomformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomformatAlignLeft:"+e.toString());
		 }
		return bottomformat;		
	}
	
	public WritableCellFormat getBottomformatAlignRight(){
		WritableCellFormat bottomformat = new WritableCellFormat(normalFont);
		try{
			bottomformat.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomformat.setAlignment(Alignment.RIGHT);
			bottomformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomformatAlignRight:"+e.toString());
		 }
		return bottomformat;		
	}
	
	public WritableCellFormat getBottomformatAlignCenter(){
		WritableCellFormat bottomformat = new WritableCellFormat(normalFont);
		try{
			bottomformat.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomformat.setAlignment(Alignment.CENTRE);
			bottomformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomformatAlignCenter:"+e.toString());
		 }
		return bottomformat;		
	}
	
	
	public WritableCellFormat getBottomleftRedAlignLeft(){
		WritableCellFormat bottomleft = new WritableCellFormat(normalRedFont);
		try{
			bottomleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bottomleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomleft.setAlignment(Alignment.LEFT);
			bottomleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomleftRedAlignLeft:"+e.toString());
		 }
		return bottomleft;		
	}
	
	public WritableCellFormat getBottomleftRedAlignRight(){
		WritableCellFormat bottomleft = new WritableCellFormat(normalRedFont);
		try{
			bottomleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bottomleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomleft.setAlignment(Alignment.RIGHT);
			bottomleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomleftRedAlignRight:"+e.toString());
		 }
		return bottomleft;		
	}
	
	public WritableCellFormat getBottomleftRedAlignCenter(){
		WritableCellFormat bottomleft = new WritableCellFormat(normalRedFont);
		try{
			bottomleft.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.LEFT, BorderLineStyle.DOUBLE);
			bottomleft.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomleft.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomleft.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomleft.setAlignment(Alignment.CENTRE);
			bottomleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomleftRedAlignCenter:"+e.toString());
		 }
		return bottomleft;		
	}
	
	public WritableCellFormat getBottomrightRedAlignLeft(){
		WritableCellFormat bottomright = new WritableCellFormat(normalRedFont);
		try{
			bottomright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bottomright.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomright.setAlignment(Alignment.LEFT);
			bottomright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomrightRedAlignLeft:"+e.toString());
		 }
		return bottomright;		
	}
	
	public WritableCellFormat getBottomrightRedAlignRedRight(){
		WritableCellFormat bottomright = new WritableCellFormat(normalRedFont);
		try{
			bottomright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bottomright.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomright.setAlignment(Alignment.RIGHT);
			bottomright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomrightRedAlignRight:"+e.toString());
		 }
		return bottomright;		
	}
	
	public WritableCellFormat getBottomrightRedAlignCenter(){
		WritableCellFormat bottomright = new WritableCellFormat(normalRedFont);
		try{
			bottomright.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomright.setBorder(Border.RIGHT, BorderLineStyle.DOUBLE);
			bottomright.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomright.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomright.setAlignment(Alignment.CENTRE);
			bottomright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomrightRedAlignCenter:"+e.toString());
		 }
		return bottomright;		
	}
	
	public WritableCellFormat getBottomformatRedAlignLeft(){
		WritableCellFormat bottomformat = new WritableCellFormat(normalRedFont);
		try{
			bottomformat.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomformat.setAlignment(Alignment.LEFT);
			bottomformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomformatRedAlignLeft:"+e.toString());
		 }
		return bottomformat;		
	}
	
	public WritableCellFormat getBottomformatRedAlignRight(){
		WritableCellFormat bottomformat = new WritableCellFormat(normalRedFont);
		try{
			bottomformat.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomformat.setAlignment(Alignment.RIGHT);
			bottomformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomformatRedAlignRight:"+e.toString());
		 }
		return bottomformat;		
	}
	
	public WritableCellFormat getBottomformatRedAlignCenter(){
		WritableCellFormat bottomformat = new WritableCellFormat(normalRedFont);
		try{
			bottomformat.setBorder(Border.TOP, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.LEFT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.RIGHT, BorderLineStyle.HAIR);
			bottomformat.setBorder(Border.BOTTOM, BorderLineStyle.DOUBLE);
			bottomformat.setVerticalAlignment(VerticalAlignment .CENTRE);
			bottomformat.setAlignment(Alignment.CENTRE);
			bottomformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBottomformatRedAlignCenter:"+e.toString());
		 }
		return bottomformat;		
	}
	
	public WritableCellFormat getBodyAlignleft(){
		WritableCellFormat bodyAlignleft = new WritableCellFormat(normalFont);
		try{
			bodyAlignleft.setVerticalAlignment(VerticalAlignment.CENTRE);
			bodyAlignleft.setAlignment(Alignment.LEFT);
			bodyAlignleft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyAlignleft:"+e.toString());
		 }
		return bodyAlignleft;
	}
	
	public WritableCellFormat getBodyAlignright(){
		WritableCellFormat bodyAlignright = new WritableCellFormat(normalFont);
		try{
			bodyAlignright.setVerticalAlignment(VerticalAlignment.CENTRE);
			bodyAlignright.setAlignment(Alignment.RIGHT);
			bodyAlignright.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyAlignright:"+e.toString());
		 }
		return bodyAlignright;
	}		
	
	public WritableCellFormat getBodyAligncenter(){
		WritableCellFormat bodyAligncenter = new WritableCellFormat(normalFont);
		try{
			bodyAligncenter.setVerticalAlignment(VerticalAlignment.CENTRE);
			bodyAligncenter.setAlignment(Alignment.CENTRE);
			bodyAligncenter.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyAligncenter:"+e.toString());
		 }
		return bodyAligncenter;
	}
	
	public WritableCellFormat getBodyH2AlignLeft(){
		WritableCellFormat bodyH2AlignLeft = new WritableCellFormat(normalFontH2);
		try{
			bodyH2AlignLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
			bodyH2AlignLeft.setAlignment(Alignment.LEFT);
			bodyH2AlignLeft.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getBodyH2AlignLeft:"+e.toString());
		 }
		return bodyH2AlignLeft;
	}
	
	public WritableCellFormat getTitleformat(){
		WritableCellFormat titleformat = new WritableCellFormat(titleFont);
		try{
			titleformat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleformat.setAlignment(Alignment.CENTRE);
			titleformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getTitleformat:"+e.toString());
		 }
		return titleformat;
	}
	
	public WritableCellFormat getDemoformat(){
		WritableCellFormat titleformat = new WritableCellFormat(normalFont);
		try{
			titleformat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleformat.setAlignment(Alignment.LEFT);
			titleformat.setWrap(false);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getTitleformat:"+e.toString());
		 }
		return titleformat;
	}
	
	public SheetSettings getSheetSettings(WritableSheet sheet){
		SheetSettings sheetSettings= sheet.getSettings(); 
		try{
			sheetSettings.setOrientation(PageOrientation.PORTRAIT);
			sheetSettings.setTopMargin(0.6);
			sheetSettings.setBottomMargin(0.6);
			sheetSettings.setLeftMargin(0.18);
			sheetSettings.setRightMargin(0.18);
			sheetSettings.setHorizontalCentre(true);
		}
		catch (Exception e){
			 System.out.println("ExcelUtil-->getSheetSettings:"+e.toString());
		 }
		return sheetSettings;
	}
}