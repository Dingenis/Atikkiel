package nl.dss.atikkiel.authenticaiton

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.security.PrivateKey
import java.util.*

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 29-5-2018
 * Description...
 */
object TokenGenerator {

    fun generate(apiKey : String,
                 key : PrivateKey,
                 expiration : Date = Date(Date().time + 1000 * 60 * 19),
                 notBefore : Date = Date()) : String {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.RS512, key)
                .setHeader(mapOf("typ" to "JWT", "alg" to "RS512"))
                .setSubject(apiKey)
                .setAudience("https://auth-sandbox.abnamro.com/oauth/token")
                .setExpiration(expiration)
                .setNotBefore(notBefore)
                .setIssuer("me")
                .compact()
    }
}