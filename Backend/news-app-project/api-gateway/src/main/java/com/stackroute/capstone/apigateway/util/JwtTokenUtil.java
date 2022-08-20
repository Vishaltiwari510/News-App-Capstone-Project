package com.stackroute.capstone.apigateway.util;

import org.springframework.stereotype.Component;

import com.stackroute.capstone.apigateway.congif.JwtConfig;
import com.stackroute.capstone.apigateway.exception.JwtTokenIncorrectStructureException;
import com.stackroute.capstone.apigateway.exception.JwtTokenMalformedException;
import com.stackroute.capstone.apigateway.exception.JwtTokenMissingException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	private final JwtConfig config;

	public JwtTokenUtil(JwtConfig config) {
		this.config = config;
	}

	

	public void validateToken(final String header) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			String[] parts = header.split(" ");
			if (parts.length != 2 || !"Bearer".equals(parts[0])) {
				throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure");	}

			Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(parts[1]);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
}