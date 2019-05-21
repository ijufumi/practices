# Answers of coding test
[SoftwareEngineerChallenge](https://github.com/Pay-Baymax/SoftwareEngineerChallenge)

## Coding task
 * [Java version](src/main/java/) 
 * [Scala version](src/main/scala/) 

## Design Question

 - データの永続化を段階的に行い、レスポンスを早くする。
   - リクエストを受けたら、MQにデータを追加しレスポンスを返す
   - MQのデータを元にRedisのデータを更新する
   - Redisのデータを元にRDBのデータを更新する  

 - ユーザを識別するIDでシャーディングを行い処理するサーバ及びデータの格納先を分ける。また、返すデータはRedisにキャッシュし、IOによる遅延を出来るだけ少なくする
 
 - メインシステムとは別に永続化するトランザクションのデータを集計するバッチを用意する。バッチでは集計する期間ごと（1分、1時間、1日、など）にプロセスを分け、互いの処理が影響しないようにする
 
 - ダウンタイムを最低限にする為に、冗長構成で組む
 
 - 全てのトランザクションのデータを永続化することで、後からデータの集計のやり直しを可能にする

### Requirements
 1. handle large write volume: Billions write events per day.

 1. handle large read/query volume: Millions merchants want to get insight about their business. Read/Query patterns are time-series related metrics.

 1. provide metrics to customers with at most one hour delay.

 1. run with minimum downtime.

 1. have the ability to reprocess historical data in case of bugs in the processing logic.
