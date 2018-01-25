package com.bridgelabz.User.Utility;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;

public class Token {
	static	private  String Key = "mykey";

	public static String generateToken( int id) {
		String token = "";
		long currentTime = System.currentTimeMillis();
		long expireTime = currentTime + (60000 * 60 * 24 * 2);
		Date date = new Date(currentTime);
		Date expireDate = new Date(expireTime);
		token = Jwts.builder().setId(String.valueOf(id)).setIssuedAt(date).signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, Key).setExpiration(expireDate).compact();
		System.out.println("Token :: " + token);
		return token;
	}
	public static int verify(String jwt) throws ExpiredJwtException {
		try {
			Claims claims = Jwts.parser().setSigningKey(Key).parseClaimsJws(jwt).getBody();
			if (claims.isEmpty())
				return 0;
			else {
				System.out.println("ID: " + claims.getId());
				System.out.println("Subject: " + claims.getSubject());
				System.out.println("Issuer: " + claims.getIssuer());
				System.out.println("Expiration: " + claims.getExpiration());
				return Integer.parseInt(claims.getId());
			}

		} catch (ExpiredJwtException e) {
			System.out.println("Token expired!!!");
			return 0;
		}catch (MissingClaimException e) {
			e.printStackTrace();
		    return  0;
		} catch (Exception e) {
			e.printStackTrace();
			 return 0;
		}
	}

}
