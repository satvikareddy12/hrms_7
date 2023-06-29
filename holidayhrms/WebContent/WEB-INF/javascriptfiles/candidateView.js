 function openModal(employeeId) {
            var modal = document.getElementById("myModal");
            modal.style.display = "block";
            // Set the URL to fetch employee details
            var url = "viewcandidate?id=" + employeeId;
            // Use AJAX to fetch the employee details and update the modal content
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var employeeDetails = this.responseText;
                    document.getElementById("modalContent").innerHTML = employeeDetails;
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
        }
        
            

        // Function to open the modal popup
        function openAddCandidatePopup() {
            var modal = document.getElementById("myModal");
            modal.style.display = "block";
            // Set the URL of the add candidate form
            var url = "candidate";
            // Use AJAX to fetch the add candidate form content and update the modal content
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var addCandidateForm = this.responseText;
                    document.getElementById("modalContent").innerHTML = addCandidateForm;
                }
            };
            xhttp.open("GET", url, true);
            xhttp.send();
        }

        
        // Function to close the modal popup
        function closeModal() {
            var modal = document.getElementById("myModal");
            modal.style.display = "none";
        }

        // Event listener to close the modal when clicking outside of it
        window.onclick = function(event) {
            var modal = document.getElementById("myModal");
            if (event.target == modal) {
                modal.style.display = "none";
            }
        };/**
 * 
 */