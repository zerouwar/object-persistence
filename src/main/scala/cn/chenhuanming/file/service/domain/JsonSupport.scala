package cn.chenhuanming.file.service.domain

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/11
  */
final case class UploadToken(token:String)
final case class ClientConfig(domain:Domain)
final case class Domain(qiniu:String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{
  implicit val uploadTokenFormat = jsonFormat1(UploadToken)
  implicit val domainFormat = jsonFormat1(Domain)
  implicit val configFormat = jsonFormat1(ClientConfig)

}
