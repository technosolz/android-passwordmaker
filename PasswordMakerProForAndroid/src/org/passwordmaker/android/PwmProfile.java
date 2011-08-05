package org.passwordmaker.android;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.passwordmaker.android.LeetConverter.LeetLevel;
import org.passwordmaker.android.LeetConverter.UseLeet;

public class PwmProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum UrlComponents {
		Protocol, Subdomain, Domain, PortPathAnchorQuery
	}
	
	
	private String name = "Default";
	private HashAlgo currentAlgo = HashAlgo.MD5;
	private transient PwmHashAlgorithm hashAlgo = PwmHashAlgorithm.get(currentAlgo);
	private UseLeet useLeet = UseLeet.NotAtAll;
	private LeetLevel leetLevel = LeetLevel.One;
	private EnumSet<UrlComponents> urlComponents = defaultUrlComponents();
	private short lengthOfPassword = 8;
	private String username = "";
	private String modifier = "";
	private String characters = CharacterSetSelection.alphaNum.getCharacterSet();
	private String passwordPrefix = "";
	private String passwordSuffix = "";
	private Set<String> pwmFavoriteInputs = new HashSet<String>();
	
	public PwmProfile() {
	}

	public PwmProfile(String name) {
		this.name = name;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		hashAlgo = PwmHashAlgorithm.get(currentAlgo);
	}

	public static EnumSet<UrlComponents> defaultUrlComponents() {
		return EnumSet.of(UrlComponents.Domain);
	}
	
	public PwmHashAlgorithm getHashAlgo() {
		return hashAlgo;
	}

	public void setHashAlgo(PwmHashAlgorithm hashAlgo) {
		this.hashAlgo = hashAlgo;
		this.currentAlgo = hashAlgo.getHashAlgo();
	}
	
	public UseLeet getUseLeet() {
		return useLeet;
	}

	public void setUseLeet(UseLeet useLeet) {
		this.useLeet = useLeet;
	}

	public LeetLevel getLeetLevel() {
		return leetLevel;
	}

	public void setLeetLevel(LeetLevel leetLevel) {
		this.leetLevel = leetLevel;
	}

	public EnumSet<UrlComponents> getUrlComponents() {
		return urlComponents;
	}

	public void setUrlComponents(EnumSet<UrlComponents> urlComponents) {
		this.urlComponents = urlComponents;
	}

	public short getLengthOfPassword() {
		return lengthOfPassword;
	}

	public void setLengthOfPassword(short lengthOfPassword) {
		this.lengthOfPassword = lengthOfPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(CharacterSetSelection characters) {
		setCharacters(characters.getCharacterSet());
	}
	
	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public String getPrefix() {
		return passwordPrefix;
	}

	public void setPrefix(String passwordPrefix) {
		this.passwordPrefix = passwordPrefix;
	}

	public String getSuffix() {
		return passwordSuffix;
	}

	public void setSuffix(String passwordSuffix) {
		this.passwordSuffix = passwordSuffix;
	}
	
	public String getName() {
		return name;
	}
	
	public HashAlgo getCurrentAlgo() {
		return currentAlgo;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHashAlgo(HashAlgo algo) {
		setHashAlgo(PwmHashAlgorithm.get(algo));
	}
	
	public Set<String> getFavorites() {
		return pwmFavoriteInputs;
	}
	
	public boolean addFavorite(String newFav) {
		return pwmFavoriteInputs.add(newFav);
	}

	public void addFavorite(List<String> favs) {
		pwmFavoriteInputs.addAll(favs);
		
	}
}
