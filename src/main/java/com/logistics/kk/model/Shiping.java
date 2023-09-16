package com.logistics.kk.model;

import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles.Lookup;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shiping_tab")
public class Shiping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shiping_id")
	private Integer id;

	@Column(name = "shiping_code")
	private String shipingCode;

	@Column(name = "shiping_ref_num")
	private String shipngRefNum;

	@Column(name = "courier_ref_num")
	private String courierRefNum;

	@Column(name = "contact_details")
	private String contactDetails;

	@Column(name = "shiping_desc")
	private String description;

	// 1...1
	@ManyToOne
	@JoinColumn(name = "so_id_fk", unique = true)
	private SaleOrder so;

	// 1...*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "shiping_id_fk")
	private Set<ShipingDtl> dtls;

	public int length() {
		return contactDetails.length();
	}

	public boolean isEmpty() {
		return contactDetails.isEmpty();
	}

	public char charAt(int index) {
		return contactDetails.charAt(index);
	}

	public int codePointAt(int index) {
		return contactDetails.codePointAt(index);
	}

	public int codePointBefore(int index) {
		return contactDetails.codePointBefore(index);
	}

	public int codePointCount(int beginIndex, int endIndex) {
		return contactDetails.codePointCount(beginIndex, endIndex);
	}

	public int offsetByCodePoints(int index, int codePointOffset) {
		return contactDetails.offsetByCodePoints(index, codePointOffset);
	}

	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		contactDetails.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	public void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
		contactDetails.getBytes(srcBegin, srcEnd, dst, dstBegin);
	}

	public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
		return contactDetails.getBytes(charsetName);
	}

	public byte[] getBytes(Charset charset) {
		return contactDetails.getBytes(charset);
	}

	public byte[] getBytes() {
		return contactDetails.getBytes();
	}

	public boolean equals(Object anObject) {
		return contactDetails.equals(anObject);
	}

	public boolean contentEquals(StringBuffer sb) {
		return contactDetails.contentEquals(sb);
	}

	public boolean contentEquals(CharSequence cs) {
		return contactDetails.contentEquals(cs);
	}

	public boolean equalsIgnoreCase(String anotherString) {
		return contactDetails.equalsIgnoreCase(anotherString);
	}

	public int compareTo(String anotherString) {
		return contactDetails.compareTo(anotherString);
	}

	public int compareToIgnoreCase(String str) {
		return contactDetails.compareToIgnoreCase(str);
	}

	public boolean regionMatches(int toffset, String other, int ooffset, int len) {
		return contactDetails.regionMatches(toffset, other, ooffset, len);
	}

	public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
		return contactDetails.regionMatches(ignoreCase, toffset, other, ooffset, len);
	}

	public boolean startsWith(String prefix, int toffset) {
		return contactDetails.startsWith(prefix, toffset);
	}

	public boolean startsWith(String prefix) {
		return contactDetails.startsWith(prefix);
	}

	public boolean endsWith(String suffix) {
		return contactDetails.endsWith(suffix);
	}

	public int hashCode() {
		return contactDetails.hashCode();
	}

	public int indexOf(int ch) {
		return contactDetails.indexOf(ch);
	}

	public int indexOf(int ch, int fromIndex) {
		return contactDetails.indexOf(ch, fromIndex);
	}

	public int lastIndexOf(int ch) {
		return contactDetails.lastIndexOf(ch);
	}

	public int lastIndexOf(int ch, int fromIndex) {
		return contactDetails.lastIndexOf(ch, fromIndex);
	}

	public int indexOf(String str) {
		return contactDetails.indexOf(str);
	}

	public int indexOf(String str, int fromIndex) {
		return contactDetails.indexOf(str, fromIndex);
	}

	public int lastIndexOf(String str) {
		return contactDetails.lastIndexOf(str);
	}

	public int lastIndexOf(String str, int fromIndex) {
		return contactDetails.lastIndexOf(str, fromIndex);
	}

	public String substring(int beginIndex) {
		return contactDetails.substring(beginIndex);
	}

	public String substring(int beginIndex, int endIndex) {
		return contactDetails.substring(beginIndex, endIndex);
	}

	public CharSequence subSequence(int beginIndex, int endIndex) {
		return contactDetails.subSequence(beginIndex, endIndex);
	}

	public String concat(String str) {
		return contactDetails.concat(str);
	}

	public String replace(char oldChar, char newChar) {
		return contactDetails.replace(oldChar, newChar);
	}

	public boolean matches(String regex) {
		return contactDetails.matches(regex);
	}

	public boolean contains(CharSequence s) {
		return contactDetails.contains(s);
	}

	public String replaceFirst(String regex, String replacement) {
		return contactDetails.replaceFirst(regex, replacement);
	}

	public String replaceAll(String regex, String replacement) {
		return contactDetails.replaceAll(regex, replacement);
	}

	public String replace(CharSequence target, CharSequence replacement) {
		return contactDetails.replace(target, replacement);
	}

	public String[] split(String regex, int limit) {
		return contactDetails.split(regex, limit);
	}

	public String[] split(String regex) {
		return contactDetails.split(regex);
	}

	public String toLowerCase(Locale locale) {
		return contactDetails.toLowerCase(locale);
	}

	public String toLowerCase() {
		return contactDetails.toLowerCase();
	}

	public String toUpperCase(Locale locale) {
		return contactDetails.toUpperCase(locale);
	}

	public String toUpperCase() {
		return contactDetails.toUpperCase();
	}

	public String trim() {
		return contactDetails.trim();
	}

	public String strip() {
		return contactDetails.strip();
	}

	public String stripLeading() {
		return contactDetails.stripLeading();
	}

	public String stripTrailing() {
		return contactDetails.stripTrailing();
	}

	public boolean isBlank() {
		return contactDetails.isBlank();
	}

	public Stream<String> lines() {
		return contactDetails.lines();
	}

	public String indent(int n) {
		return contactDetails.indent(n);
	}

	public String stripIndent() {
		return contactDetails.stripIndent();
	}

	public String translateEscapes() {
		return contactDetails.translateEscapes();
	}

	public <R> R transform(Function<? super String, ? extends R> f) {
		return contactDetails.transform(f);
	}

	public String toString() {
		return contactDetails.toString();
	}

	public IntStream chars() {
		return contactDetails.chars();
	}

	public IntStream codePoints() {
		return contactDetails.codePoints();
	}

	public char[] toCharArray() {
		return contactDetails.toCharArray();
	}

	public String formatted(Object... args) {
		return contactDetails.formatted(args);
	}

	public String intern() {
		return contactDetails.intern();
	}

	public String repeat(int count) {
		return contactDetails.repeat(count);
	}

	public Optional<String> describeConstable() {
		return contactDetails.describeConstable();
	}

	public String resolveConstantDesc(Lookup lookup) {
		return contactDetails.resolveConstantDesc(lookup);
	}

	public Shiping(Integer id, String shipingCode, String shipngRefNum, String courierRefNum, String contactDetails,
			String description, SaleOrder so, Set<ShipingDtl> dtls) {
		super();
		this.id = id;
		this.shipingCode = shipingCode;
		this.shipngRefNum = shipngRefNum;
		this.courierRefNum = courierRefNum;
		this.contactDetails = contactDetails;
		this.description = description;
		this.so = so;
		this.dtls = dtls;
	}

	public Shiping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleOrderDetails getSo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDtls(Set<ShipingDtl> shipingSet) {
		// TODO Auto-generated method stub
		
	}

	public Object getDtls() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
