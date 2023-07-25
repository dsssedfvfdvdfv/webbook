function startCountdown(days) {
    const countdownElement = document.getElementById('countdown');
  
    // Chuyển đổi số ngày thành milliseconds
    const countdownTime = days * 24 * 60 * 60 * 1000;
  
    // Lưu thời gian hiện tại
    const startTime = Date.now();
  
    // Cập nhật đồng hồ đếm ngược mỗi giây
    const countdownInterval = setInterval(updateCountdown, 1000);
  
    function updateCountdown() {
      // Tính toán thời gian còn lại
      const elapsedTime = Date.now() - startTime;
      const remainingTime = countdownTime - elapsedTime;
  
      // Kiểm tra nếu đã hết thời gian đếm ngược
      if (remainingTime <= 0) {
        clearInterval(countdownInterval);
        countdownElement.textContent = 'Đã hết thời gian!';
        return;
      }
  
      // Chuyển đổi thời gian còn lại thành ngày, giờ, phút và giây
      const daysLeft = Math.floor(remainingTime / (24 * 60 * 60 * 1000));
      const hoursLeft = Math.floor((remainingTime % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000));
      const minutesLeft = Math.floor((remainingTime % (60 * 60 * 1000)) / (60 * 1000));
      const secondsLeft = Math.floor((remainingTime % (60 * 1000)) / 1000);
  
      document.getElementById("days").textContent = daysLeft;
      document.getElementById("hours").textContent = hoursLeft;
      document.getElementById("minutes").textContent = minutesLeft;
      document.getElementById("seconds").textContent = secondsLeft;
    }
  }
  
  // Gọi hàm startCountdown với số ngày bạn muốn đếm ngược
  startCountdown(1);