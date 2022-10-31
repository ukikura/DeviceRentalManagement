package app.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageUtil {
//	/**
//	 * appパッケージ内のメソッドを開始する際にメッセージを出力します.
//	 * @param joinPoint
//	 */
//	@Before("execution(* app.*.*.*(..))")
//	public void beforeMessage(JoinPoint joinPoint) {
//		System.out.println("----- 開始：" + joinPoint.getSignature() + " −−−−−");
//	}
//	/**
//	 * appパッケージ内のメソッドを終了する際にメッセージを出力します.
//	 * @param joinPoint
//	 */
//	@After("execution(* app.*.*.*(..))")
//	public void afterMessage(JoinPoint joinPoint) {
//		System.out.println("----- 終了：" + joinPoint.getSignature() + " -----");
//	}
//	/**
//	 * app.serviceパッケージ内のメソッドがreturnをする際にメッセージを出力します.
//	 * @param joinPoint
//	 */
//	@AfterReturning("execution(* app.service.*.*(..))")
//	public void afterMessageReturn() {
//		System.out.println("----- 情報処理を終了します. -----");
//	}
}
