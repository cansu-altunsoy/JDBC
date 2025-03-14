
  Feature: executeQuery Testleri
    #executequery select sorgularında (bir resualtset elde ederiz)
    #executeupdate  ise insert,delete and update sorgularında gecerli  (bir integer deger elde ederim)


     Background:    # her test oncesi calisacak satir
       * Database baglantisi kurulur



      #Database içindeki "deposits" toblosunda
      # "amount" değeri 100$ ile 500$ arasında olan
      # user_id’leri doğrulayınız

    @executeQuery01
    Scenario: Amount degerine gore ID sorgulama testi

          * Database baglantisi kurulur
         * SQL Querysi hazirla ve calistir
         * Sonuclari dogrula
         * Database baglantisini sonlandir


       #Database içindeki "cron_schedules" tablosunda ilk 2 kaydın
       # "name" bilgisini doğrulayınız

      @executeQuery02
      Scenario: cron_schedules tablosundan "Name" bilgisi dogrulama testi.


        * (cron_schedules) SQL Query'si calistirilir
        * (cron_schedules) sonuclari dogrulanir
        * Database baglantisi sonlandirilir






















