package com.jb.coupon_system_spring.util;

import com.jb.coupon_system_spring.beans.ClientType;
import com.jb.coupon_system_spring.beans.Company;
import com.jb.coupon_system_spring.beans.Customer;
import com.jb.coupon_system_spring.exceptions.LoginException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWT {
    private String signatureAlgorithm= SignatureAlgorithm.HS256.getJcaName();
    //our secret key
    private String secretKey="this+is+my+key+and+it+must+be+256+bit+long+fnjvnfjvvee";
    //decode the secret key
    private Key decodedSecretKey=new SecretKeySpec
            (Base64.getDecoder().decode(secretKey),this.signatureAlgorithm);

    public String generateToken(String email/*, String password*/){
        Map<String,Object> claims=new HashMap<>();
        claims.put("name","Admin");
        claims.put("client type",ClientType.ADMIN);
        return "Bearer "+ createToken(claims,email);
    }
    public String generateToken(Company company){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",company.getId());
        claims.put("name",company.getName());
        claims.put("client type", ClientType.COMPANY);
        return "Bearer "+ createToken(claims,company.getEmail());
    }
    public String generateToken(Customer customer){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",customer.getId());
        claims.put("first name",customer.getFirstName());
        claims.put("last name",customer.getLastName());
        claims.put("client type", ClientType.CUSTOMER);
        return "Bearer "+ createToken(claims,customer.getEmail());
    }

    public String checkUser(String token) throws MalformedJwtException, LoginException {
        Claims claims= extractAllClaims(token.replace("Beares ",""));
        switch ((ClientType) claims.get("client type")){
            case ADMIN:
                return generateToken(claims.getSubject());
            case COMPANY:
                Company company=new Company();
                company.setId((int)claims.get("id"));
                company.setName((String) claims.get("name"));
                company.setEmail(claims.getSubject());
                return generateToken(company);
            case CUSTOMER:
                Customer customer=new Customer();
                customer.setId((int)claims.get("id"));
                customer.setFirstName((String) claims.get("first name"));
                customer.setLastName((String)claims.get("last name"));
                customer.setEmail(claims.getSubject());
                return generateToken(customer);
        }
        throw new LoginException("unauthorized user");
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException, MalformedJwtException{
        JwtParser jwtParser=Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token.replace("Bearer ","")).getBody();
    }

    private String createToken(Map<String, Object> claims, String email) {
        Instant now=Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(decodedSecretKey)
                .compact();
    }
}
