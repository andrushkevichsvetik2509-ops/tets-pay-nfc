TETS PAY — проект для сборки APK через GitHub Actions

ВАЖНО: это игровой демонстрационный терминал. Он не проводит банковские платежи и не списывает реальные деньги. NFC между двумя Android-телефонами зависит от аппаратной поддержки обоих устройств и версии Android.

КАК СОБРАТЬ APK ТОЛЬКО С ТЕЛЕФОНА
1. Загрузите этот ZIP в пустой GitHub-репозиторий как один файл.
2. Откройте репозиторий в браузере и перейдите Code → Codespaces → Create codespace on main. Если Codespaces недоступен, этот способ не сработает.
3. В терминале Codespaces выполните, заменив имя ZIP на фактическое имя загруженного файла:
   unzip -o TETS_PAY_GITHUB_BUILD.zip -d .
   rm TETS_PAY_GITHUB_BUILD.zip
   git add .
   git commit -m "Add TETS PAY Android project"
   git push
4. Откройте вкладку Actions репозитория. Дождитесь зелёного завершения Build TETS PAY APK.
5. Откройте выполненный workflow → Artifacts → TETS-PAY-debug-APK и скачайте APK.
6. Установите APK на телефоны. Для NFC включите NFC в системных настройках.

GitHub сам не распаковывает ZIP при загрузке. Распаковка выполняется командой unzip внутри Codespaces. APK ещё не был собран и протестирован на реальных телефонах.
