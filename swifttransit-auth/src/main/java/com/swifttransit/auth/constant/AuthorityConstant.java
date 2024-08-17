package com.swifttransit.auth.constant;

public interface AuthorityConstant {

    String PrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCr2me0/GAeJjug5y4N8FUpyVp8LDXBzklzyOCarYOeottTkJsrTZf22veRbKGj/I7QRjg3CAZcVLGPaco0m9SGhkw0W9uqvui/DGYvsj6Brzbyk9Ie5s1O3XSi4rzTkZnBd8ghKYndTV8/EL+u/cFBrW5fj6gC68YxmpD/XOcaK4AwkQgaO9TuoSG9kNr8xWM/OpgEGgwrtai7XqOHngpgPzSNFbum8GCeuH3hcI8A5BxxZJ1z1NdsvFhFKQXEOqCPKbY9NLZ3FTTOMscprYyX7rtsGcjkhC9EQen0pnk1xkFF+d7GeQgUtSNnOM6/VOJc34TfZVQzyD87IXiUbBCVAgMBAAECggEAHhSMTwRW0agpC0SzzbpJudb1+OKnJS11s2fPkpt4/JkucT3QPz/lb87Lgx+j2meQWtnTqKRFsr8bVkoy28sD4+kZaRfEByrD4/7oWwRkxv/Tixm/L2wAN0PxkLpFyXWEl3XfKJjT+hG7MHd+ycM+Yr/X4C+tvZcl2ZRfuWb6R5zYtXa9XsyhiAThyj66V7T4ezpDDQkSBhnxluvrSoTC2ExvhOzo6sALFVdRNiP+hQP5CzTw6qazi4wUEvoJhSF3lQqtc+1eYXdMDCzalUi81QiY6HYNhhcVwLXyAWdqophbXS2z7VzKckb9NEz7VUAZZJrG3jlZdRNaD867YiwOMQKBgQDSZzj47I2aIbErBaqYIp40M3ItV7CxgrQJTP32GDf9C1SQeISF5qcViuIxn45V60gz5MfEDLKXfAD2CtukTKU++vDvMZorAlHhcol6g6LPr0nXjJ5SSDzyWlm2g9VvUangceU6qJR0F/ceBdFF3PNnJAMkvtWCv3Iz1HT8m4x9ZQKBgQDRGH8q2DrlN+cuSiJyfRAy8QI0fj8vfBmxT1AKjvKZkXpMH1B0w6PogHXIYMPuHIK3loytlQDIE93mN+lyYSCuvoXWSnT8CUPnGysapSGGtlgLRCMKGom2LQOvRgxk/jRs48pB3b/7pvg5KOq0FvoioEF3CnJbLBcWDy4pgMnrcQKBgQDRiiNfJJbRTYSYCAihgrhz3Fh1d8NsxXJvYNH7aJs50zLgvmWcxNzZw3sJWJH0V2GM7OdWBB9IZiK+5olf00Uut9ODI68Z7jGFiQAJl1dp5nRvyodSAsdPxVNbRWgmePnpoQRdqs5N4CAnMjv8i9OMgHzw92zDzc36eDgZ6v+7UQKBgAF/EGHpHDrJtKDTLP3uwS/sI5zmjmMzmyd2rtb0gO3yaZKj4rqDL+gZCz682ooYsCLKNAWF/HRyF/Nu0P/djBNUD7ch30Zydobv3dYZ+mxxX53tBecrm3KvTchyUuJiI1PoKWlhQd92KW6XSV6yj2WyCeXlMD5QyXWB9E2du1QxAoGAYTXdse62GruhQmb++djQpbLvuX/05fvcGrF8GCmZqL3qL+5jmATgAsC4QOhkla7ZMaThhoL3JwctD/sFP5kJDndC2THrm6V0NxPNL4kezHziGahJ9BGM3XCeIHDLeAG4qnXmW4qJ19zSYp18t/Hyg/6zWEFmm/RvXyEipDWSrA4=";

    /**
     * accessToken 过期时间 1个小时
     */
    int ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60;

    /**
     * refreshToken 过期时间7天
     */
    int REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;
}
